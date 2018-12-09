package cmsc434.refridgerator;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class msgPg extends baseNav {
    protected cAdapter msgr;
    private ListView listView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.msg_pg, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
        context = msgPg.this;
        TextView title = findViewById(R.id.title);
        title.setText("Messages");
        Button wr = findViewById(R.id.butwr);
        if(!read)
            setMsg();
        read = true;
        wr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext(), msgLv.class);
                startActivityForResult(myIntent, 1);
            }
        });
        setEd();
    }

    private void copy(String s)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN, s);
        clipboard.setPrimaryClip(clip);
    }

    protected void setEd()
    {
        if(listView == null)
            listView = findViewById(R.id.msg_scr);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String[] opt1 = {"Copy","Delete","Edit","Hide"};
                String[] opt2 = {"Copy","Hide"};
                if(msgr.isHidden(pos))
                {
                    opt1[3] = "Unhide";
                    opt2[1] = "Unhide";
                }
                AlertDialog.Builder b2 = new AlertDialog.Builder(context);
                final int p = pos;
                if(msgr.getSender(pos).equals("You")) {
                    b2.setItems(opt1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // the user clicked on nSen[i]
                                if(i == 0)
                                    copy(msgr.getMsg(p));
                                else if(i == 1) {
                                    AlertDialog.Builder al = new AlertDialog.Builder(context);

                                    al.setTitle("Are you sure?");
                                     al.setMessage("Message: " + msgr.getMsg(p));

                                    // Set an EditText view to get user input

                                    al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            msgr.delete(p);
                                        }
                                    });
                                    al.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            // Canceled.
                                        }
                                    });
                                    al.show();
                                    }
                                else if(i == 2)
                                {
                                    AlertDialog.Builder al = new AlertDialog.Builder(context);

                                    al.setTitle("Edit Message");
                                   // al.setMessage(msgr.getMsg(p));

                                // Set an EditText view to get user input
                                    final EditText in = new EditText(context);

                                    in.setText(msgr.getMsg(p), TextView.BufferType.EDITABLE);
                                    al.setView(in);

                                    al.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            msgr.edit(p,in.getText() + "");
                                        }
                                    });
                                    al.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            // Canceled.
                                        }
                                    });

                                    al.show();
                                }
                                else //Hide
                                {
                                    msgr.hide(p);
                                }
                            }

                    });

                }
                else
                {
                        b2.setItems(opt2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // the user clicked on opt2[i]
                                if(i == 0)
                                    copy(msgr.getMsg(p));
                                else if(i == 1)
                                msgr.hide(p);
                            }
                        });
                }
                b2.show();
                return true;
            }
        });
    }

    protected void setMsg()
    {
        String[] s = readFile();
       // String[] s = readAsset();

        int sz = s.length;
        String[] msgArr = Arrays.copyOfRange(s, 0, sz/3);
        String[] nameArr = Arrays.copyOfRange(s, sz/3, 2*sz/3);
        String[] recArr = Arrays.copyOfRange(s, 2*sz/3, sz);

        ArrayList<String> msgArray = new ArrayList<String>();
        ArrayList<String> nameArray = new ArrayList<String>();
        ArrayList<String> recArray = new ArrayList<String>();

        msgArray.addAll(Arrays.asList(msgArr));
        nameArray.addAll(Arrays.asList(nameArr));
        recArray.addAll(Arrays.asList(recArr));

        msgr = new cAdapter(this, nameArray, msgArray, recArray);

        listView = findViewById(R.id.msg_scr);
        listView.setAdapter(msgr);
    }

    protected String[] readAsset()
    {
        InputStream is = null;
        String s;
        ArrayList<String> msgs = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        try {
            is = getAssets().open("defmsg.txt");
            BufferedReader sc = new BufferedReader(new InputStreamReader(is));

            while((s = sc.readLine()) != null)
            {
                msgs.add(s);
                names.add(sc.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sz = msgs.size();
        String[] msgbrd = new String[sz*2];
        for(int i = 0; i < sz; i++)
            msgbrd[i] = msgs.get(i);
        for(int i = sz; i < sz*2; i++)
            msgbrd[i] = names.get(i - sz);
        if(is != null)
        {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }//end try-catch
        }//end if
        return msgbrd;
    }

    protected String[] readFile()
    {
        FileInputStream is = null;
        Context context = getApplicationContext();
        String s;
        ArrayList<String> msgs = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> recip = new ArrayList<String>();

        try {
            is = context.openFileInput("defmsg.txt");
            BufferedReader sc = new BufferedReader(new InputStreamReader(is));

            while((s = sc.readLine()) != null)
            {
                msgs.add(s);
                names.add(sc.readLine());
                recip.add(sc.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sz = msgs.size();
        String[] msgbrd = new String[sz*3];
        for(int i = 0; i < sz; i++)
            msgbrd[i] = msgs.get(i);
        for(int i = sz; i < sz*2; i++)
            msgbrd[i] = names.get(i - sz);
        for(int i = sz*2; i < sz*3; i++)
            msgbrd[i] = recip.get(i - sz*2);
        if(is != null)
        {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }//end try-catch
        }//end if
        return msgbrd;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String msg = data.getStringExtra("msg");
                String name = data.getStringExtra("name");
                String rec = data.getStringExtra("recip");
                msgr.add(name, msg, rec);
                msgr.notifyDataSetChanged();

            }
        }

    }


    protected void onPause()
    {
        super.onPause();
        if(msgr!=null)
        {
            nArray = msgr.getNames();
            mArray = msgr.getMsgs();
            rArray = msgr.getRecip();
            hArray = msgr.getHid();
            eArray = msgr.getEd();
        }
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
        if(nArray != null) {
            msgr = new cAdapter(this, nArray, mArray, rArray, hArray, eArray);
            listView = findViewById(R.id.msg_scr);
            listView.setAdapter(msgr);
        }

/*
        String[] s = readFile();
        int sz = s.length;
        String[] msgArr = Arrays.copyOfRange(s, 0, sz/2);
        String[] nameArr = Arrays.copyOfRange(s, sz/2, sz);
        msgr.msgArray = msgArr;
        msgr.nameArray = nameArr;
        msgr.notifyDataSetChanged();
        //msgr.update(msgArray, nameArray);
        */
    }

  /*  public void onRestart()
    {
        onResume();
    }*/
}
