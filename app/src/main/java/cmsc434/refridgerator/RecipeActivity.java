package cmsc434.refridgerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmsc434.refridgerator.MealPlanning.RecipeSelectionActivity;
import cmsc434.refridgerator.adapters.RecipeRecyclerViewAdapter;
import cmsc434.refridgerator.model.RecipeEntry;


public class RecipeActivity extends baseNav implements RecipeRecyclerViewAdapter.ItemClickListener {

    private RecipeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recipe_activity, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);

        TextView title = findViewById(R.id.title);
        title.setText("Meal Planning - Recipes");

        List<RecipeEntry> entries = new ArrayList<>();
        entries.add(new RecipeEntry(R.drawable.salmon, "salmon cakes"));
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta"));
        entries.add(new RecipeEntry(R.drawable.pumpkin, "pumpkin and sausage soup"));
        entries.add(new RecipeEntry(R.drawable.mushroompork, "mushroom pork chops"));
        entries.add(new RecipeEntry(R.drawable.padthai, "sukhothai pad thai"));
        entries.add(new RecipeEntry(R.drawable.limechicken, "spicy garlic lime chicken"));

        int cols = 2;

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, cols));
        adapter = new RecipeRecyclerViewAdapter(entries);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    public void onItemClick(View view, int position) {

        Intent intent = new Intent(RecipeActivity.this, RecipeSelectionActivity.class);
        intent.putExtra("recipe", ""+position);

        startActivity(intent);


    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);
    }



}
