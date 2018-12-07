package cmsc434.refridgerator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class shopping extends baseNav {

    private ListView mShoppingList;
    //private EditText mItemEdit;
    private Button mAddButton;

    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate()");
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.shopping_list, contentFrame, true);
        //setContentView(R.layout.shopping_list);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_shop);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Shopping List");
        //Button rd = findViewById(R.id.butrd);

        mShoppingList = (ListView) findViewById(R.id.shopping_listView);
        //mItemEdit = (EditText) findViewById(R.id.item_editText);
        mAddButton = (Button) findViewById(R.id.add_item_button);


        //mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //mShoppingList.setAdapter(mAdapter);

        GroceryListWriter glw = new GroceryListWriter(getApplicationContext());

        Log.e("Tag", "About to read from the grocery list file.");
        final ArrayList<String> groceryList = glw.readGroceryList();

        ArrayList<GroceryListItem> groceryListItems = new ArrayList<GroceryListItem>();

        for (String item: groceryList) {
            ArrayList<String> values = glw.readGroceryListItemValues(item);
            int quantity = Integer.parseInt(values.get(0));
            String quantityType = values.get(1);
            String brand = values.get(2);
            GroceryListItem list_item = new GroceryListItem(item, quantity, quantityType, brand);
            groceryListItems.add(list_item);
        }

        CustomGroceryListAdaptor adaptor = new CustomGroceryListAdaptor(this, groceryListItems);
        mShoppingList.setAdapter(adaptor);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(shopping.this, add_grocery_item.class);
                startActivity(newIntent);
                //String item = mItemEdit.getText().toString();
                //Log.e("ADDING", "Trying to add: " + item + " to the grocery list");
               //GroceryListWriter glw2 = new GroceryListWriter(shopping.this);
                //ArrayList<String> current_list = glw2.readGroceryList();
                /*if (current_list.contains(item)) {
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

                */
            }
        });

        mShoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String grocery_item = (String) ((TextView) view).getText();
                GroceryListItem selected = (GroceryListItem) adapterView.getAdapter().getItem(i);
               // GroceryListItem selected = (GroceryListItem) adapterView.getSelectedItem();
                String grocery_item = selected.getItemName();
                Intent newIntent = new Intent(shopping.this, add_grocery_item.class);
                newIntent.putExtra("item", grocery_item);
                startActivity(newIntent);
            }
        });
       setEdit();

    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_shop);
        mi.setChecked(true);
    }

    protected void setEdit(){
            /*
        mItemEdit.addTextChangedListener(new TextWatcher() {

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
        */
    }
}


