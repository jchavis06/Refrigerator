package cmsc434.refridgerator;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class baseNav extends AppCompatActivity {
    protected DrawerLayout mDrawer;
    protected NavigationView navView;
    private static boolean user_exists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*check for existing user
        * if user
        * do nothing
        * else go to add user*/
        if(!user_exists){
            user_exists = true;
            Intent myIntent = new Intent(getApplicationContext(), Add_User.class);
            startActivity(myIntent);

        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        mDrawer = findViewById(R.id.drawer_layout);
        setNav();
        setTBar();
    }

    public void onResume() {
        super.onResume();
        Menu menu = navView.getMenu();
        MenuItem mi = menu.findItem(R.id.nav_home);
        mi.setChecked(true);
    }

    protected void setNav()
    {
        navView = findViewById(R.id.nav_view);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        Intent myIntent;
                        // close drawer when item is tapped
                        mDrawer.closeDrawers();

                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_home:
                                myIntent = new Intent(getApplicationContext(), homePg.class);
                                //myIntent.putExtra("key", 4); //Optional parameters
                                startActivity(myIntent);
                                break;
                            case R.id.nav_inventory:
                                myIntent = new Intent(getApplicationContext(), inventory.class);
                                startActivity(myIntent);
                                break;
                            case R.id.nav_msg:
                                myIntent = new Intent(getApplicationContext(), msgPg.class);
                                startActivity(myIntent);
                                break;
                            case R.id.nav_shop:
                                myIntent = new Intent(getApplicationContext(), shopping.class);
                                startActivity(myIntent);
                                break;
                            case R.id.nav_meals:
                                myIntent = new Intent(getApplicationContext(), RecipeActivity.class);
                                startActivity(myIntent);
                                break;
                        }
                        return true;
                    }
                });
    }//end setNav

    protected void setTBar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    } //This is makes the button open the drawer


}
