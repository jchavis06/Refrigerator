package cmsc434.refridgerator.MealPlanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import cmsc434.refridgerator.R;
import cmsc434.refridgerator.baseNav;

public class RecipeSelectionActivity extends baseNav {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        FrameLayout contentFrame = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recipe_selection, contentFrame, true);
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);

        TextView title = findViewById(R.id.title);
        title.setText("Recipe");

        BufferedReader reader = null;
        Map<String, StringBuilder> results = null;
        try{
            reader = new BufferedReader(new InputStreamReader(getAssets().open("recipe/"+intent.getStringExtra("recipe")+".txt")));
            results = new HashMap<>();
            String line = "";
            String currentKey = "";
            while((line = reader.readLine()) != null){
                if(line.startsWith("[") && line.endsWith("]")){
                    currentKey = line.substring(1, line.length() - 1);
                    results.put(currentKey, new StringBuilder());
                }
                else{
                    results.get(currentKey).append(line).append("\n");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Map<String, Integer> imageResolver = new HashMap<String, Integer>();
        imageResolver.put("0", R.drawable.salmon);
        imageResolver.put("1", R.drawable.pasta);
        imageResolver.put("2", R.drawable.pumpkin);
        imageResolver.put("3", R.drawable.mushroompork);
        imageResolver.put("4", R.drawable.padthai);
        imageResolver.put("5", R.drawable.limechicken);

        ((TextView)findViewById(R.id.name)).setText(results.get("name").toString());
        ((TextView)findViewById(R.id.ingredients)).setText(results.get("ingredients").toString());
        ((TextView)findViewById(R.id.instructions)).setText(results.get("instructions").toString());
        ((ImageView)findViewById(R.id.imageView)).setImageResource(imageResolver.get(intent.getStringExtra("recipe")));
    }


    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);
    }


}
