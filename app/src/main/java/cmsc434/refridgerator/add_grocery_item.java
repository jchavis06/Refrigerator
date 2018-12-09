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
    private boolean editing;
    private ArrayAdapter<String> mAdapter;
    private String grocery_item_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.editing = false;
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate()");
        TextView title = findViewById(R.id.title);
        setContentView(R.layout.add_grocery_item);
        title.setText("Add Item");
        //Button rd = findViewById(R.id.butrd);

        String[] quantities = {"1", "2","3","4","5", "6", "7","8","9","10"};

        Bundle extras =  getIntent().getExtras();
        grocery_item_old = "";
        if (extras != null) {
            grocery_item_old = extras.getString("item");
        }
        int old_quantity;
        String old_quantity_type;
        String brand;
        String comment;




        itemName = (EditText) findViewById(R.id.itemName);
        brandName = (EditText) findViewById(R.id.brandText);
        comments = (EditText) findViewById(R.id.commentsText);
        qty = (Spinner) findViewById(R.id.quantitySpinner);
        qtyLabel = (Spinner) findViewById(R.id.quantityTypeSpinner);
        addItem = (Button) findViewById(R.id.add_grocery_item_button);

        final String[] quantityTypes= {"Gallons", "Cartons"};

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        qty.setAdapter(aa);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantityTypes);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        qtyLabel.setAdapter(aa2);


        if (!grocery_item_old.equals("")) {
            GroceryListWriter gw = new GroceryListWriter(getApplicationContext());
            ArrayList<String> vals = gw.readGroceryListItemValues(grocery_item_old);
            //we are editing a grocery list item
            editing = true;
            addItem.setText("Done Editing");
            old_quantity = Integer.parseInt(vals.get(0));
            old_quantity_type = vals.get(1);
            brand = vals.get(2);
            comment = vals.get(3);

            itemName.setText(grocery_item_old);
            brandName.setText(brand);
            comments.setText(comment);

            ArrayAdapter myAdap = (ArrayAdapter) qty.getAdapter(); //cast to an ArrayAdapter

            int spinnerPosition = myAdap.getPosition("" + old_quantity);
            qty.setSelection(spinnerPosition);

            ArrayAdapter myAdap2 = (ArrayAdapter) qtyLabel.getAdapter(); //cast to an ArrayAdapter

            int spinnerPosition2 = myAdap2.getPosition("" + old_quantity_type);
            qtyLabel.setSelection(spinnerPosition2);

            //qty.setPrompt("" + old_quantity);
            //qtyLabel.setPrompt(old_quantity_type);
        }


        qty.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimaryText));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


        qtyLabel.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimaryText));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all of the information and store it
                String grocery_item = itemName.getText().toString();
                int quantity = Integer.parseInt(qty.getSelectedItem().toString());
                String quantityType = qtyLabel.getSelectedItem().toString();
                String brand = brandName.getText().toString();
                String comment = comments.getText().toString();

                GroceryListWriter gw = new GroceryListWriter(getApplicationContext());

                ArrayList<String> currentList = gw.readGroceryList();
                if (editing == true) {
                    gw.editGroceryListItem(grocery_item_old, grocery_item, quantity, quantityType, brand, comment);
                } else if (grocery_item.equals("")) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(add_grocery_item.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(add_grocery_item.this);
                    }
                    builder.setTitle("Add Item")
                            .setMessage("Please Fill Out The Name Of The Item.")
                            .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                } else if (currentList.contains(grocery_item)) {
                    //already exists in the list. popup to tell them no.
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(add_grocery_item.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(add_grocery_item.this);
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
                    return;
                } else {
                    gw.addToGroceryList(grocery_item, quantity, quantityType, brand, comment);
                }
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
