package cmsc434.refridgerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class msgPg extends baseNav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.msg_pg, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Messages");
        //Button rd = findViewById(R.id.butrd);
        Button wr = findViewById(R.id.butwr);
        wr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext(), msgLv.class);
                startActivity(myIntent);
            }
        });
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_msg);
        mi.setChecked(true);
    }
}
