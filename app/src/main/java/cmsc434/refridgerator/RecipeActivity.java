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

        entries.add(new RecipeEntry(R.drawable.salmon, "Salmon Cakes"));
        entries.add(new RecipeEntry(R.drawable.pasta, "Pasta"));
        entries.add(new RecipeEntry(R.drawable.pumpkin, "Pumpkin and Sausage Soup"));
        entries.add(new RecipeEntry(R.drawable.mushroompork, "Mushroom Pork Chops"));
        entries.add(new RecipeEntry(R.drawable.padthai, "Sukhothai Pad Thai"));
        entries.add(new RecipeEntry(R.drawable.limechicken, "Spicy Garlic Lime Chicken"));

        String [] filter = getIntent().getStringArrayExtra("filter");
        if(filter != null && filter.length > 0) {
            List<RecipeEntry> filteredEntries = new ArrayList<>();
            for (RecipeEntry entry : entries) {
                for(String f: filter) {
                    if (entry.getDescription().toLowerCase().contains(f)){
                        filteredEntries.add(entry);
                    }
                }
            }
            entries = filteredEntries;
        }


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
