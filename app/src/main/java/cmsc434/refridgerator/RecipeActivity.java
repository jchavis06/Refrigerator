package cmsc434.refridgerator;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        title.setText("Meal Planning");

        List<RecipeEntry> entries = new ArrayList<>();
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta1"));
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta2"));
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta3"));
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta4"));
        entries.add(new RecipeEntry(R.drawable.pasta, "pasta5"));

        int cols = 2;

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, cols));
        adapter = new RecipeRecyclerViewAdapter(entries);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    public void onItemClick(View view, int position) {
        System.out.println(position);
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);
    }



}
