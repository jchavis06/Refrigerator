package cmsc434.refridgerator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;


public class subAct extends baseNav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.sub_act, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_inventory);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Inventory");


        /* Intent intent = getIntent();
        ImageView i = findViewById(R.id.imageView1);
        String value = intent.getStringExtra("key"); //if it's a string stored.
        if(value.equals("4"))
            i.setVisibility(ImageView.VISIBLE);
        else
            i.setVisibility(ImageView.INVISIBLE);
        */
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_inventory);
        mi.setChecked(true);
    }
}
