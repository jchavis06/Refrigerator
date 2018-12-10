package cmsc434.refridgerator.MealPlanning;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;

import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Map<String, List> results = null;
        try{
            reader = new BufferedReader(new InputStreamReader(getAssets().open("recipe/"+intent.getStringExtra("recipe")+".txt")));
            results = new HashMap<>();
            String line = "";
            String currentKey = "";
            while((line = reader.readLine()) != null){
                if(line.startsWith("[") && line.endsWith("]")){
                    currentKey = line.substring(1, line.length() - 1);
                    results.put(currentKey, new ArrayList<String>());
                }
                else{
                    results.get(currentKey).add(line);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Map<String, Integer> imageResolver = new HashMap<>();
        imageResolver.put("0", R.drawable.salmon);
        imageResolver.put("1", R.drawable.pasta);
        imageResolver.put("2", R.drawable.pumpkin);
        imageResolver.put("3", R.drawable.mushroompork);
        imageResolver.put("4", R.drawable.padthai);
        imageResolver.put("5", R.drawable.limechicken);

        ((TextView)findViewById(R.id.name)).setText(results.get("name").isEmpty() ? "Untitled" : results.get("name").get(0).toString());
        //((TextView)findViewById(R.id.ingredients)).setText(results.get("ingredients").toString());

         LinearLayout ingredients = findViewById(R.id.ingredients_list);
        List<String> ingredientList = results.get("ingredients");
        final List<String> owners = new ArrayList<>();
        owners.add("Will");
        owners.add("Jake");
        owners.add("Michael");
        owners.add("John");
        owners.add("Shared");
        final View main = findViewById(R.id.linearLayout2);
        for(String s: ingredientList){

            LinearLayout ingredientEntry = new LinearLayout(this);
            ingredientEntry.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ingredientEntry.setOrientation(LinearLayout.HORIZONTAL);
            TextView current = new TextView(this);
            current.setText(s);
            current.setGravity(Gravity.LEFT);
            System.out.println(owners.size() - 1);

            if(s.toLowerCase().contains("onion")){
                current.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v){
                        int randomIndex = (int)(Math.random()*(owners.size()));
                        Snackbar.make(main, getString(R.string.allergy, owners.get(randomIndex)   ),Snackbar.LENGTH_LONG ).show();
                    }
                });
                current.setTextColor(Color.parseColor("red"));
            }



            TextView owner = new TextView(this);
            owner.setText(" ["+owners.get((int)(Math.random() * owners.size())) + "]");


            owner.setGravity(Gravity.RIGHT);

            ingredientEntry.addView(current);
            ingredientEntry.addView(owner);
            ingredients.addView(ingredientEntry);



        }

        //((TextView)findViewById(R.id.instructions)).setText(results.get("instructions").toString());

        LinearLayout instructions = findViewById(R.id.instructions_list);
        List<String> instructionsList = results.get("instructions");
        for(String s: instructionsList){
            System.out.println("INSTRUCTION: "+s);
            TextView current = new TextView(this);
            current.setText(s);
            instructions.addView(current);
        }
        ((ImageView)findViewById(R.id.imageView)).setImageResource(imageResolver.get(intent.getStringExtra("recipe")));
    }


    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_meals);
        mi.setChecked(true);
    }


}
