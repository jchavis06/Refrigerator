package cmsc434.refridgerator.MealPlanning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import cmsc434.refridgerator.R;
import cmsc434.refridgerator.RecipeActivity;
import cmsc434.refridgerator.add_grocery_item;
import cmsc434.refridgerator.baseNav;

public class IngredientsActivity extends baseNav {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.meal_planning_ingredients, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);

        TextView title = findViewById(R.id.title);
        title.setText("Meal Planning");

        final CheckBox chickenCheckBox = (CheckBox)findViewById(R.id.checkBox7);

        final CheckBox allergy1 = (CheckBox) findViewById(R.id.checkBoxAllergic1);
        final CheckBox allergy2 = (CheckBox) findViewById(R.id.checkBoxAllergic2);

        allergy1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(allergy1.isChecked()){
                    //System.out.println("Checked");
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(IngredientsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(IngredientsActivity.this);
                    }
                    builder.setTitle("Add Item")
                            .setMessage("John is allergic to dairy! Continue to use ingredient? ")
                            .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                }
                            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dont add to things
                            allergy1.setChecked(false);
                        }
                    })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


        allergy2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(allergy2.isChecked()){
                    //System.out.println("Checked");
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(IngredientsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(IngredientsActivity.this);
                    }
                    builder.setTitle("Add Item")
                            .setMessage("Jake is allergic to gluten! Continue to use ingredient? ")
                            .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                }
                            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dont add to things
                            allergy1.setChecked(false);
                        }
                    })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(IngredientsActivity.this, RecipeActivity.class);

                if(chickenCheckBox.isChecked()) {
                    next.putExtra("filter", new String[]{"chicken"});
                }

                startActivity(next);
            }
        });


    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);
    }
}
