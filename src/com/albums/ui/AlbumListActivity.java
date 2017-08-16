package com.albums.ui;

import java.util.UUID;
import com.albumlists.R;
import com.albums.controller.AlbumLoadable;
import com.albums.controller.AlbumLoader;
import com.albums.model.AlbumList;
import com.albums.ui.dialog.AlbumListSettingsDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class AlbumListActivity extends BaseAlbumActivity implements AlbumLoadable {
    AlbumList currentList;
    Toolbar toolbar;
    ListView albumListView;
    RelativeLayout mainLayoutView;
    AlbumLoader albumLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentList = loadList();
        this.albumLoader = new AlbumLoader(this, this);
        loadUI();
        albumLoader.loadImages(this.currentList.getAlbums());
    }

    /**
     * Load current list between activities from metalist using ID
     * @return - album list from meta list
     */
    private AlbumList loadList() {
        Intent intent = getIntent();
        String albumIdStr = intent.getStringExtra("com.albums.albumListId");
        UUID albumId = UUID.fromString(albumIdStr);
        AlbumList albumList = BaseAlbumActivity.getAlbumFromId(albumId);
        return albumList;
    }

    /**
     * Set up UI for the AlbumList
     */
    private void loadUI() {
        setContentView(R.layout.activity_album_list);
        mainLayoutView = (RelativeLayout) findViewById(R.id.activity_album_list);
        this.toolbar = (Toolbar) findViewById(R.id.album_list_toolbar);
        setSupportActionBar(this.toolbar);
    }

    /**
     * Reload UI with any changes
     * Also, this and populateAlbumListView are kind of trying to do the same thing when adding a new album to the list
     */
    @Override
    public void refreshLists() {
        saveMetaList();
        setTitle(this.currentList.getName());
    }

    public AlbumList getList() {
        return this.currentList;
    }

    /**
     * Callback method originating from search API response and passing through search controller
     * Create/populate listview
     * @param resultSet - List of Albums returned from search
     */
    @Override
    public void populateAlbumListView() {
        saveMetaList();
        if (albumListView == null) {
            albumListView = createAlbumListView();
            this.mainLayoutView.addView(albumListView);
        }
        if (this.currentList.getAlbums() != null) {
            AlbumListArrayAdapter adapter = new AlbumListArrayAdapter(this, R.layout.album_list_item, this.currentList.getAlbums());
            albumListView.setAdapter(adapter);
        }
    }

    /**
     * Create and position empty ListView
     * @return - empty ListView in correct position
     */
    private ListView createAlbumListView() {
        ListView returnView = (ListView) View.inflate(this, R.layout.album_list_view, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.BELOW, this.toolbar.getId());
        returnView.setLayoutParams(layoutParams);
        return returnView;
    }

    /**
     * Create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.album_list_menu, menu);
        return true;
    }

    /**
     * Menu listener
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!super.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
            case R.id.action_list_settings:
                AlbumListSettingsDialog settingsDialog = new AlbumListSettingsDialog(this, currentList);
                settingsDialog.show();
                return true;
            default:
                return false;
            }
        }
        return false;
    }
}
