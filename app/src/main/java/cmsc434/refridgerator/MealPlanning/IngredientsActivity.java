package cmsc434.refridgerator.MealPlanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import cmsc434.refridgerator.R;
import cmsc434.refridgerator.RecipeActivity;
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
