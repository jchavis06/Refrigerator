package cmsc434.refridgerator;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGroceryListAdaptor extends BaseAdapter {

    private Context context; //context
    private ArrayList<GroceryListItem> items; //data source of the list adapter

    //public constructor
    public CustomGroceryListAdaptor(Context context, ArrayList<GroceryListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.shopping_list_row, parent, false);
        }

        // get current item to be displayed
        GroceryListItem currentItem = (GroceryListItem) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.itemNameView);
        TextView textViewItemQuantity = (TextView)
                convertView.findViewById(R.id.itemQuantityView);
        TextView textViewItemQuantityType = (TextView)
                convertView.findViewById(R.id.itemQuantityTypeView);
        TextView textViewItemBrand = (TextView)
                convertView.findViewById(R.id.itemBrandView);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getItemName());
        textViewItemQuantity.setText("" + currentItem.getQuantity());

        textViewItemQuantityType.setText(currentItem.getQuantityType());
        textViewItemBrand.setText("" + currentItem.getBrand());
        // returns the view for the current row
        return convertView;
    }
}
