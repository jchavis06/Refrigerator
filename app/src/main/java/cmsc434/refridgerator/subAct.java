package cmsc434.refridgerator;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.app.AlertDialog;


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

        String[] items = new String[]{"Shared", "Will", "John","Jake","Michael"};

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        ListView lView = findViewById(R.id.list_view);
        lView.setAdapter(listAdapter);


        lView.setClickable(true);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position indexes list from 0 - n
                AlertDialog alertDialog = new AlertDialog.Builder(subAct.this).create();

                if(position == 0){
                    alertDialog.setTitle("Shared");
                    alertDialog.setMessage("Eggs\nCheddar Cheese\nWhole Milk\nButter\nHam\nYogurt");
                }else if(position == 1){
                    alertDialog.setTitle("Will");
                    alertDialog.setMessage("2% Milk\nChicken Breast\nBacon\nTurkey\nSpinach\nBell Peppers\nSteak");
                }else if(position == 2){
                    alertDialog.setTitle("John");
                    alertDialog.setMessage("Pizza\nChicken Breast\nCanadian Bacon\nSpring Salad Mix\nChicken Salad\n" +
                            "Roast Beef\nTurkey\nGround Beef");
                }else if(position == 3){
                    alertDialog.setTitle("Jake");
                    alertDialog.setMessage("Sandwich Bread\nPotatoes\nChicken Thighs\nPork Chops\nShredded Cheese" +
                            "Turkey\nTurkey Bacon\nTacos\n");
                }else if(position == 4){
                    alertDialog.setTitle("Michael");
                    alertDialog.setMessage("Apples\nChicken Wings\nPork Sausage\nChicken Sausage\nLettuce\n" +
                            "Tomatoes\nQuinoa\n");
                }else{
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Tried to access nonexistent part of list");
                }


                alertDialog.show();
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
