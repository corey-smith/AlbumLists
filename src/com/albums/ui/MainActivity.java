package com.albums.ui;

import java.util.ArrayList;
import java.util.List;
import com.albumlists.R;
import com.albums.api.API;
import com.albums.model.AlbumList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar mainToolbar;
    private List<AlbumList> metaList;

    /**
     * Create main context, create menubar and drawer mostly
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the toolbar out of main.xml and set it as the actionbar for the app
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
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // TODO:Flesh this out more, probably break this out into an actual class
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.drawer_open, R.string.drawer_closed) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };
        populateDrawer();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
    
    /**
     * Load album lists and populate them into the drawer
     */
    private void populateDrawer() {
        metaList = new ArrayList<AlbumList>();
        metaList.add(new AlbumList("test1"));
        metaList.add(new AlbumList("test2"));
        metaList.add(new AlbumList("test3"));
        MetaListArrayAdapter adapter = new MetaListArrayAdapter(this, R.layout.meta_list_item, metaList);
        ListView drawerListView = (ListView) findViewById(R.id.meta_list_view);
        drawerListView.setAdapter(adapter);
        drawerListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent albumListIntent = new Intent(MainActivity.this, AlbumListActivity.class);
                AlbumList currentAlbumList = metaList.get(position);
                String albumListJson = currentAlbumList.asJson();
                albumListIntent.putExtra("com.albums.currentAlbumListJson", albumListJson);
                startActivity(albumListIntent);
            }
        });
        Log.d("DRAWER COUNT", Integer.toString(drawerListView.getCount()));
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
