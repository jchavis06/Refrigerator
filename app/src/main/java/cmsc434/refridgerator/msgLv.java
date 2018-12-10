package cmsc434.refridgerator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class msgLv extends baseNav {
    ArrayList<String> nSen;
    ArrayList<String> Sen;
    Context context;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = msgLv.this;
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.msg_lv, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Leave a Message");
        Button wr = findViewById(R.id.msg_post);
        txt = findViewById(R.id.postFrid);
        wr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                EditText e = findViewById(R.id.msg_input);
                String[] s = new String[3];
                s[0] =  e.getText() + "";
                s[1] = "You";
                s[2]= "Fridge";
                writ(s);
                finish();
            }
        });
        setPpl();
        setRec();
        setEdit();
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
    }

    public void writ(String[] s){
        Intent ret = new Intent();
        ret.putExtra("msg", s[0]);
        ret.putExtra("name",s[1]);
        if(!Sen.isEmpty())
        {
            s[2] = "";
            for(int i = 0; i < Sen.size() - 1; i++)
            {
                s[2] += Sen.get(i) + ", ";
            }
            s[2] += Sen.get(Sen.size() - 1);
        }
        ret.putExtra("recip",s[2]);
        setResult(RESULT_OK, ret);
    }

    protected void setEdit()
    {
        EditText e = findViewById(R.id.msg_input);
        e.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                for(int i = s.length()-1; i >= 0; i--){
                    if(s.charAt(i) == '\n'){
                        s.delete(i, i + 1);
                        return;
                    }
                }
            }
        });
    }

    protected void setRec()
    {
        Button cr = findViewById(R.id.msgRec);
        cr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                String[] opt = {"Add Recipient","Remove Recipient"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //builder.setTitle("Pick a color");
                builder.setItems(opt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        // the user clicked on opt[i]
                        AlertDialog.Builder b2 = new AlertDialog.Builder(context);
                        if(i == 0)
                        {
                            String[] nSe = nSen.toArray(new String[0]);
                            if(nSe.length == 0)
                                b2.setTitle("No one to add");
                            else {
                                b2.setTitle("Add Recipient");
                                b2.setItems(nSe, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        // the user clicked on nSen[i]
                                        String s = nSen.get(i);
                                        nSen.remove(i);
                                        Sen.add(s);
                                        String text = "To: " + TextUtils.join(", ", Sen);
                                        txt.setText(text);
                                    }
                                });
                            }
                        }
                        else
                        {
                            String[] Se = Sen.toArray(new String[0]);
                            if(Se.length == 0)
                                b2.setTitle("No one to remove");
                            else {
                                b2.setTitle("Remove Recipient");
                                b2.setItems(Se, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        // the user clicked on Sen[i]
                                        String s = Sen.get(i);
                                        Sen.remove(i);
                                        nSen.add(s);
                                        if(Sen.isEmpty())
                                            txt.setText("Posting to Fridge");

                                    }
                                });
                            }
                        }
                        b2.show();
                    }

                });
                builder.show();
            }
        });
    }

    protected void setPpl()
    {
        String[] arr = {"Ana", "Dad", "Mom", "Tim", "You"};
        nSen = new ArrayList<String>();
        Sen = new ArrayList<String>();
        nSen.addAll(Arrays.asList(arr));


    }
}
