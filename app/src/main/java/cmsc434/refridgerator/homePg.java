package cmsc434.refridgerator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class homePg extends baseNav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.home_pg, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_home);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Home");

        Context Context = getApplicationContext();
        String DestinationFile = Context.getFilesDir().getPath() + File.separator + "defmsg.txt";
        if (!new File(DestinationFile).exists()) {
            try {
                copyToStorage(Context, "defmsg.txt", DestinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }//end try-catch
        }//end if
        setButtons();
    }

    protected void setButtons()
    {
        Button wr = findViewById(R.id.butms);
        wr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext(), msgPg.class);
                startActivity(myIntent);
            }
        });
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_home);
        mi.setChecked(true);
    }

    private void copyToStorage(Context Context, String SourceFile, String DestinationFile) throws IOException {
        InputStream is = Context.getAssets().open(SourceFile);
        OutputStream os = new FileOutputStream(DestinationFile);
        copy(is, os);
        os.flush();
        os.close();
        is.close();
    }

    private void copy(InputStream Input, OutputStream Output) throws IOException
    {
        byte[] b = new byte[5120];
        int length = Input.read(b);
        while (length > 0) {
            Output.write(b, 0, length);
            length = Input.read(b);
        }
    }


}

