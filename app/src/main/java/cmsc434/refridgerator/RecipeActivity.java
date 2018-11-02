package cmsc434.refridgerator;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cmsc434.refridgerator.adapters.RecipeRecyclerViewAdapter;
import cmsc434.refridgerator.model.RecipeEntry;


public class RecipeActivity extends baseNav implements RecipeRecyclerViewAdapter.ItemClickListener {

    private RecipeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



}
