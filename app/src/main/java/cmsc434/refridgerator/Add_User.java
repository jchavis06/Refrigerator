package cmsc434.refridgerator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.app.AlertDialog;
import android.os.Handler;


public class Add_User extends AppCompatActivity {

    private Spinner spinnerLabel;

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

        String[] allergies = {"None", "Peanuts", "Tree Nuts", "Shellfish", "Soy", "Gluten", "Milk", "Vegetarian", "Vegan", "Other"};


        TextView title = findViewById(R.id.title);
        title.setText("Add User");

        spinnerLabel = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter allergies2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,allergies);
        allergies2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spinnerLabel.setAdapter(allergies2);

        spinnerLabel.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimaryText));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        FloatingActionButton icon = findViewById(R.id.floatingActionButton);

        icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Add_User.this).create();
                alertDialog.setTitle("Serial Number");
                alertDialog.setMessage("The serial number of your fridge can be located in the bottom-right of its screen\n");
                alertDialog.show();

            }
        });


        Button button = findViewById(R.id.button_2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textIn = findViewById(R.id.editText10);
                String name = textIn.getText().toString();
               // AlertDialog alertDialog = new AlertDialog.Builder(Add_User.this).create();
                //alertDialog.setTitle("Success!");
                //alertDialog.setMessage("Thank you for signing up, " + name + ".");
                //alertDialog.show();
                Intent i = new Intent(Add_User.this, shopping.class);
                i.putExtra("name", "Success!\nThank you for signing up, " + name + ".");
                setResult(RESULT_OK, i);
                finish();
                startActivity(i);
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

