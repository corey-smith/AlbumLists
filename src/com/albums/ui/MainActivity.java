package com.albums.ui;

import com.albumlists.R;
import com.albums.api.API;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar mainToolbar;

    /**
     * Create main context, create menubar and drawer mostly
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the toolbar out of main.xml and set it as the actionbar for the app
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        createDrawer();
        AppSettings.initialize(this);
        API.initialize();
    }

    /**
     * Function to create drawer/menu items related to drawer
     */
    private void createDrawer() {
        // TODO:Flesh this out more, probably break this out into an actual class
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.drawer_open, R.string.drawer_closed) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    /**
     * Create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Menu listener
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.search:
            SearchDialog searchDialog = new SearchDialog(this);
            searchDialog.show();
            return true;
        default:
            // this should just be the overflow menu item
            return super.onOptionsItemSelected(item);
        }
    }
    
    /**
     * Get keyboard for this context. This should probably be an interface for all of the contexes once more exist
     * @return - current InputMethodManager for this context (keyboard)
     */
    public InputMethodManager getKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return inputMethodManager;
    }
}
