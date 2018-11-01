package cmsc434.refridgerator;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;
import android.os.Handler;


public class Add_User extends baseNav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.add_user, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_inventory);
        mi.setChecked(true);


        TextView title = findViewById(R.id.title);
        title.setText("Add User");


        Button button = findViewById(R.id.button_2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textIn = findViewById(R.id.editText10);
                String name = textIn.getText().toString();

                AlertDialog alertDialog = new AlertDialog.Builder(Add_User.this).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("Thank you for signing up, " + name + ".");
                alertDialog.show();

                finish();
            }
        });



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

