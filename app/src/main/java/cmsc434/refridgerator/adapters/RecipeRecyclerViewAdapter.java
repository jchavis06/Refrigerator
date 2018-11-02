package cmsc434.refridgerator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cmsc434.refridgerator.R;
import cmsc434.refridgerator.model.RecipeEntry;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> {

    private List<RecipeEntry> recipeEntries;
    private ItemClickListener clickListener;

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;

        public RecipeViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.recipe_image);
            textView = view.findViewById(R.id.recipe_description);
            view.setOnClickListener(this);
        }

        public void onClick(View view){
            if(clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }

    }

    public RecipeRecyclerViewAdapter(List<RecipeEntry> recipeEntries){
        this.recipeEntries = recipeEntries;

    }

    public RecipeRecyclerViewAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new RecipeViewHolder(cardView);
    }

    public void onBindViewHolder(RecipeViewHolder holder, int position){
        holder.imageView.setImageResource(recipeEntries.get(position).getImage());
        holder.textView.setText(recipeEntries.get(position).getDescription());
    }

    public int getItemCount(){
        return recipeEntries.size();
    }

    public void setClickListener(ItemClickListener listener){
        this.clickListener = listener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

}
