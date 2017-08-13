package com.albums.ui;

import com.albumlists.R;
import com.albums.api.API;
import com.albums.model.AlbumList;
import com.albums.ui.dialog.AlbumSettingsDeleteDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MainActivity extends BaseAlbumActivity {
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
     * Load album lists and populate them into the drawer, add listeners for drawer items
     */
    private void populateDrawer() {
        MetaListArrayAdapter adapter = new MetaListArrayAdapter(this, R.layout.meta_list_item, metaList);
        ListView drawerListView = (ListView) findViewById(R.id.meta_list_view);
        drawerListView.setAdapter(adapter);
        drawerListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openAlbumListActivity(position);
            }
        });
        drawerListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openAlbumListSettingsDeleteDialog(position);
                return true;
            }
        });
        Log.d("DRAWER COUNT", Integer.toString(drawerListView.getCount()));
    }
    
    @Override
    public void refreshListSettings() {
        populateDrawer();
    }
    
    /**
     * Transition to AlbumListActivity - should be called on click of list in drawer
     * @param position - position in drawer list
     */
    private void openAlbumListActivity(int position) {
        Intent albumListIntent = new Intent(MainActivity.this, AlbumListActivity.class);
        AlbumList currentAlbumList = metaList.get(position);
        String albumListJson = currentAlbumList.asJson();
        albumListIntent.putExtra("com.albums.currentAlbumListJson", albumListJson);
        startActivity(albumListIntent);
    }
    
    /**
     * Open Settings/Delete dialog for album lists
     * This should be called on long clicks from the drawer
     * @param position - position of item in metalist/drawer
     */
    private void openAlbumListSettingsDeleteDialog(int position) {
        AlbumSettingsDeleteDialog settingDeleteDialog = new AlbumSettingsDeleteDialog(this, position);
        settingDeleteDialog.show();
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
}
