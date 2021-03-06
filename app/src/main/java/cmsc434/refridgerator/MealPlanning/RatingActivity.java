package cmsc434.refridgerator.MealPlanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cmsc434.refridgerator.R;
import cmsc434.refridgerator.RecipeActivity;
import cmsc434.refridgerator.baseNav;

public class RatingActivity extends baseNav {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.meal_planning_rating, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);

        TextView title = findViewById(R.id.title);
        title.setText("Meal Planning");


        }
}
