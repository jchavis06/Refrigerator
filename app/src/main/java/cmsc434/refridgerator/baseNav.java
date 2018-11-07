package cmsc434.refridgerator;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cmsc434.refridgerator.MealPlanning.UsersActivity;

public class baseNav extends AppCompatActivity {
    protected DrawerLayout mDrawer;
    protected NavigationView navView;
    private static boolean user_exists = false;

    protected static boolean reset_default = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*check for existing user
        * if user
        * do nothing
        * else go to add user*/
        if(!user_exists){
            user_exists = true;
            Intent myIntent = new Intent(getApplicationContext(), Add_User.class);
            startActivityForResult(myIntent, 1);
        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        mDrawer = findViewById(R.id.drawer_layout);
        setNav();
        setTBar();
    }

    public void onResume()
    {
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

                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_home:
                                myIntent = new Intent(getApplicationContext(), homePg.class);
                                mDrawer.closeDrawers();
                                //myIntent.putExtra("key", 4); //Optional parameters
                                startActivity(myIntent);
                                break;
                            case R.id.nav_inventory:
                                myIntent = new Intent(getApplicationContext(), inventory.class);
                                startActivity(myIntent);
                                mDrawer.closeDrawers();
                                break;
                            case R.id.nav_msg:
                                myIntent = new Intent(getApplicationContext(), msgPg.class);
                                startActivity(myIntent);
                                mDrawer.closeDrawers();
                                break;
                            case R.id.nav_shop:
                                myIntent = new Intent(getApplicationContext(), shopping.class);
                                startActivity(myIntent);
                                mDrawer.closeDrawers();
                                break;
                            case R.id.nav_meals:
                                myIntent = new Intent(getApplicationContext(), UsersActivity.class);
                                startActivity(myIntent);
                                mDrawer.closeDrawers();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                Toast toast = Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 178);
                ((TextView)((LinearLayout)toast.getView()).getChildAt(0))
                        .setGravity(Gravity.CENTER_HORIZONTAL);
                toast.show();
            }
        }
    }

}
