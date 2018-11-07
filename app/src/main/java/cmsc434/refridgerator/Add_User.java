package cmsc434.refridgerator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;
import android.os.Handler;


public class Add_User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.add_user);
        setTBar();
        //FrameLayout contentFrame = findViewById(R.id.content_frame);
        //getLayoutInflater().inflate(R.layout.add_user, contentFrame, true);
        //Menu menu = navView.getMenu();
        //MenuItem mi = menu.findItem(R.id.nav_inventory);
        //mi.setChecked(true);

        TextView title = findViewById(R.id.title);
        title.setText("Add User");


        Button button = findViewById(R.id.button_2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textIn = findViewById(R.id.editText10);
                String name = textIn.getText().toString();
               // AlertDialog alertDialog = new AlertDialog.Builder(Add_User.this).create();
                //alertDialog.setTitle("Success!");
                //alertDialog.setMessage("Thank you for signing up, " + name + ".");
                //alertDialog.show();
                Intent i = new Intent();
                i.putExtra("name", "Success!\nThank you for signing up, " + name + ".");
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }


    protected void setTBar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }
}

