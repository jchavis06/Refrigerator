package cmsc434.refridgerator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.app.AlertDialog;

public class inventory extends baseNav {
    public void editDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(inventory.this).create();
        alertDialog.setTitle("Edit");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Remove", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                int t = 1;

            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                int t = 1;


            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Edit", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                int t = 1;


            }
        });
        alertDialog.show();
        return;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.inventory, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_inventory);
        mi.setChecked(true);
        TextView title = findViewById(R.id.title);
        title.setText("Inventory");


        final TableRow tableR1 = findViewById(R.id.tableR1);
        final TextView r1Food = findViewById(R.id.r1Food);
        final TextView r1Cat = findViewById(R.id.r1Cat);
        final TextView r1Quant = findViewById(R.id.r1Quant);
        final TextView r1Del = findViewById(R.id.r1Del);

        final TableRow tableR2 = findViewById(R.id.tableR2);
        final TextView r2Food = findViewById(R.id.r2Food);
        final TextView r2Cat = findViewById(R.id.r2Cat);
        final TextView r2Quant = findViewById(R.id.r2Quant);
        final TextView r2Del = findViewById(R.id.r2Del);

        final TableRow tableR3 = findViewById(R.id.tableR3);
        final TextView r3Food = findViewById(R.id.r3Food);
        final TextView r3Cat = findViewById(R.id.r3Cat);
        final TextView r3Quant = findViewById(R.id.r3Quant);
        final TextView r3Del = findViewById(R.id.r3Del);

        final TableRow tableR4 = findViewById(R.id.tableR4);
        final TextView r4Food = findViewById(R.id.r4Food);
        final TextView r4Cat = findViewById(R.id.r4Cat);
        final TextView r4Quant = findViewById(R.id.r4Quant);
        final TextView r4Del = findViewById(R.id.r4Del);

        final TableRow tableR5 = findViewById(R.id.tableR5);
        final TextView r5Food = findViewById(R.id.r5Food);
        final TextView r5Cat = findViewById(R.id.r5Cat);
        final TextView r5Quant = findViewById(R.id.r5Quant);
        final TextView r5Del = findViewById(R.id.r5Del);

        final TableRow tableR6 = findViewById(R.id.tableR6);
        final TextView r6Food = findViewById(R.id.r6Food);
        final TextView r6Cat = findViewById(R.id.r6Cat);
        final TextView r6Quant = findViewById(R.id.r6Quant);
        final TextView r6Del = findViewById(R.id.r6Del);

        final TableRow tableR7 = findViewById(R.id.tableR7);
        final TextView r7Food = findViewById(R.id.r7Food);
        final TextView r7Cat = findViewById(R.id.r7Cat);
        final TextView r7Quant = findViewById(R.id.r7Quant);
        final TextView r7Del = findViewById(R.id.r7Del);

        final TableRow tableR8 = findViewById(R.id.tableR8);
        final TextView r8Food = findViewById(R.id.r8Food);
        final TextView r8Cat = findViewById(R.id.r8Cat);
        final TextView r8Quant = findViewById(R.id.r8Quant);
        final TextView r8Del = findViewById(R.id.r8Del);

        final TableRow tableR9 = findViewById(R.id.tableR9);
        final TextView r9Food = findViewById(R.id.r9Food);
        final TextView r9Cat = findViewById(R.id.r9Cat);
        final TextView r9Quant = findViewById(R.id.r9Quant);
        final TextView r9Del = findViewById(R.id.r9Del);

        final TableRow tableR10 = findViewById(R.id.tableR10);
        final TextView r10Food = findViewById(R.id.r10Food);
        final TextView r10Cat = findViewById(R.id.r10Cat);
        final TextView r10Quant = findViewById(R.id.r10Quant);
        final TextView r10Del = findViewById(R.id.r10Del);

        final TableRow tableR11 = findViewById(R.id.tableR11);
        final TextView r11Food = findViewById(R.id.r11Food);
        final TextView r11Cat = findViewById(R.id.r11Cat);
        final TextView r11Quant = findViewById(R.id.r11Quant);
        final TextView r11Del = findViewById(R.id.r11Del);


        Button button = findViewById(R.id.catButton);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               // R.color.colorOldBlack
                tableR1.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r1Food.setText("Milk");
                r1Cat.setText("Dairy");
                r1Quant.setText("4");


                tableR2.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r2Food.setText("Cheddar Cheese");
                r2Cat.setText("Dairy");
                r2Quant.setText("1");

                tableR3.setBackgroundColor(getResources().getColor(R.color.oldHaloBlue));
                r3Food.setText("Eggs");
                r3Cat.setText("Dairy");
                r3Quant.setText("18");

                tableR4.setBackgroundColor(getResources().getColor(R.color.oldHaloRed));
                r4Food.setText("Chicken");
                r4Cat.setText("Meat");
                r4Quant.setText("3");

                tableR5.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r5Food.setText("Hamburgers");
                r5Cat.setText("Meat");
                r5Quant.setText("12");

                tableR6.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r6Food.setText("Spinach");
                r6Cat.setText("Vegetables");
                r6Quant.setText("2");
                r6Del.setText("Edit");

                tableR7.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r7Food.setText("Broccoli");
                r7Cat.setText("Vegetables");
                r7Quant.setText("1");
                r7Del.setText("Edit");

                tableR8.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r8Food.setText("Bell Peppers");
                r8Cat.setText("Vegetables");
                r8Quant.setText("4");
                r8Del.setText("Edit");

                tableR9.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r9Food.setText("Cake");
                r9Cat.setText("Dessert");
                r9Quant.setText("1");
                r9Del.setText("Edit");

                tableR10.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r10Food.setText("Ice Cream");
                r10Cat.setText("Dessert");
                r10Quant.setText("1");
                r10Del.setText("Edit");

                tableR11.setBackgroundColor(getResources().getColor(R.color.oldHaloRed));
                r11Food.setText("Ice Cream");
                r11Cat.setText("Dessert");
                r11Quant.setText("1");
                r11Del.setText("Edit");


            }
        });



        Button userButton = findViewById(R.id.userButton);

        userButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                tableR1.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r1Food.setText("Cheddar Cheese");
                r1Cat.setText("Dairy");
                r1Quant.setText("1");

                tableR2.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r2Food.setText("Hamburgers");
                r2Cat.setText("Meat");
                r2Quant.setText("12");

                tableR3.setBackgroundColor(getResources().getColor(R.color.oldHaloOrange));
                r3Food.setText("Bell Peppers");
                r3Cat.setText("Vegetables");
                r3Quant.setText("4");

                tableR4.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r4Food.setText("Milk");
                r4Cat.setText("Dairy");
                r4Quant.setText("4");

                tableR5.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r5Food.setText("Cake");
                r5Cat.setText("Dessert");
                r5Quant.setText("1");

                tableR6.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r6Food.setText("Spinach");
                r6Cat.setText("Vegetables");
                r6Quant.setText("2");
                r6Del.setText("Edit");

                tableR7.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r7Food.setText("Broccoli");
                r7Cat.setText("Vegetables");
                r7Quant.setText("1");
                r7Del.setText("Edit");

                tableR8.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r8Food.setText("Ice Cream");
                r8Cat.setText("Dessert");
                r8Quant.setText("1");
                r8Del.setText("Edit");

                tableR9.setBackgroundColor(getResources().getColor(R.color.oldHaloBlue));
                r9Food.setText("Eggs");
                r9Cat.setText("Dairy");
                r9Quant.setText("18");
                r9Del.setText("Edit");

                tableR10.setBackgroundColor(getResources().getColor(R.color.oldHaloRed));
                r10Food.setText("Chicken");
                r10Cat.setText("Meat");
                r10Quant.setText("3");
                r10Del.setText("Edit");

                tableR11.setBackgroundColor(getResources().getColor(R.color.oldHaloRed));
                r11Food.setText("Ice Cream");
                r11Cat.setText("Dessert");
                r11Quant.setText("1 ");
                r11Del.setText("Edit");

            }
        });

        Button myFoodButton = findViewById(R.id.foodButton);

        myFoodButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tableR1.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r1Food.setText("Spinach");
                r1Cat.setText("Vegetables");
                r1Quant.setText("2");

                tableR2.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r2Food.setText("Broccoli");
                r2Cat.setText("Vegetables");
                r2Quant.setText("1");

                tableR3.setBackgroundColor(getResources().getColor(R.color.oldHaloGreen));
                r3Food.setText("Ice Cream");
                r3Cat.setText("Dessert");
                r3Quant.setText("1");

                tableR4.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r4Food.setText("Milk");
                r4Cat.setText("Dairy");
                r4Quant.setText("4");

                tableR5.setBackgroundColor(getResources().getColor(R.color.colorOldBlack));
                r5Food.setText("Cake");
                r5Cat.setText("Dessert");
                r5Quant.setText("1");

                tableR6.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r6Food.setText("");
                r6Cat.setText("");
                r6Quant.setText("");
                r6Del.setText("");

                tableR7.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r7Food.setText("");
                r7Cat.setText("");
                r7Quant.setText("");
                r7Del.setText("");

                tableR8.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r8Food.setText("");
                r8Cat.setText("");
                r8Quant.setText("");
                r8Del.setText("");

                tableR9.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r9Food.setText("");
                r9Cat.setText("");
                r9Quant.setText("");
                r9Del.setText("");


                tableR10.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r10Food.setText("");
                r10Cat.setText("");
                r10Quant.setText("");
                r10Del.setText("");

                tableR11.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                r11Food.setText("");
                r11Cat.setText("");
                r11Quant.setText("");
                r11Del.setText("");

            }
        });

        r1Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r2Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r3Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r4Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r5Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r6Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r7Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r8Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r9Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r10Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        r11Del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editDialog();

            }

        });

        FloatingActionButton icon = findViewById(R.id.floatingActionButton2);

        icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(inventory.this).create();
                alertDialog.setTitle("Add Items");
                alertDialog.setMessage("To add items, use the scanner on the right side of your fridge.");
                alertDialog.show();

            }
        });


    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_inventory);
        mi.setChecked(true);
    }
}
