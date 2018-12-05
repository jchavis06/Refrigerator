package cmsc434.refridgerator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class add_grocery_item extends baseNav{

    private ListView mShoppingList;
    private EditText mItemEdit;
    private Button mAddButton;

    private EditText itemName;
    private EditText brandName;
    private EditText comments;
    private Spinner qty;
    private Spinner qtyLabel;
    private Button addItem;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate()");
        TextView title = findViewById(R.id.title);
        setContentView(R.layout.add_grocery_item);
        title.setText("Add Item");
        //Button rd = findViewById(R.id.butrd);

        String[] quantities = {"1", "2","3","4","5"};




        itemName = (EditText) findViewById(R.id.itemName);
        brandName = (EditText) findViewById(R.id.brandText);
        comments = (EditText) findViewById(R.id.commentsText);
        qty = (Spinner) findViewById(R.id.quantitySpinner);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        qty.setAdapter(aa);


        qty.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) view).setTextColor(getApplicationContext().getColor(R.color.colorPrimaryText));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        qtyLabel = (Spinner) findViewById(R.id.quantityTypeSpinner);

        String[] quantityTypes= {"Gallons", "Cartons"};

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantityTypes);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        qtyLabel.setAdapter(aa2);

        qtyLabel.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) view).setTextColor(getApplicationContext().getColor(R.color.colorPrimaryText));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        addItem = (Button) findViewById(R.id.add_grocery_item_button);


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(add_grocery_item.this, shopping.class);
                //newIntent.putExtra("item", grocery_item);
                startActivity(newIntent);
            }
        });
        /*
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                Log.e("ADDING", "Trying to add: " + item + " to the grocery list");
                GroceryListWriter glw2 = new GroceryListWriter(shopping.this);
                ArrayList<String> current_list = glw2.readGroceryList();
                if (current_list.contains(item)) {
                    //print error message
                    Log.e("TAG","Grocery list already contains this item.");


                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(shopping.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(shopping.this);
                    }
                    builder.setTitle("Add Item")
                            .setMessage("This item is already in the grocery list.")
                            .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();


                } else {
                    mAdapter.add(item);
                    glw2.addToGroceryList(item, "");
                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");
                }
            }
        });

        mShoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String grocery_item = (String) ((TextView) view).getText();
                Intent newIntent = new Intent(shopping.this, grocery_item.class);
                newIntent.putExtra("item", grocery_item);
                startActivity(newIntent);
            }
        });


        */


    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_shop);
        mi.setChecked(true);
    }
}
