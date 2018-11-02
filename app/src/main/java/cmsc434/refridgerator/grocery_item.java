package cmsc434.refridgerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class grocery_item extends baseNav {
    private Button saveButton;
    private Button removeButton;
    private EditText editText;

    GroceryListWriter glw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_item);
        Bundle extras = getIntent().getExtras();
        final String grocery_item = extras.getString("item");
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText("Edit " + grocery_item);

        saveButton = (Button) findViewById(R.id.save_button);
        removeButton = (Button) findViewById(R.id.remove_item_button);
        editText = (EditText) findViewById(R.id.editText);

        glw = new GroceryListWriter(getApplicationContext());
        String description = glw.readGroceryListItemDescription(grocery_item);
        editText.setText(description);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is where we save the file and go back to the grocery list view.
                String desc = editText.getText().toString();
                Log.d("DESCRIPTION", "Description: " + desc);
                glw.editGroceryListItemDescription(grocery_item, desc);
                Intent myIntent = new Intent(getApplicationContext(), shopping.class);
                startActivity(myIntent);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is where we remove the item from the grocery list
                glw.removeFromGroceryList(grocery_item);
                Intent myIntent = new Intent(getApplicationContext(), shopping.class);
                startActivity(myIntent);
            }
        });
    }


    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_shop);
        mi.setChecked(true);
    }
}

