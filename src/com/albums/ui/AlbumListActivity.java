package com.albums.ui;

import java.util.List;
import java.util.UUID;
import com.albumlists.R;
import com.albums.model.Album;
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

public class AlbumListActivity extends BaseAlbumActivity {
    AlbumList currentList;
    Toolbar toolbar;
    ListView albumListView;
    RelativeLayout mainLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentList = loadList();
        loadUI();
        populateAlbumListView(this.currentList.getAlbums());
    }

    private AlbumList loadList() {
        Intent intent = getIntent();
        String albumIdStr = intent.getStringExtra("com.albums.albumListId");
        UUID albumId = UUID.fromString(albumIdStr);
        AlbumList albumList = BaseAlbumActivity.getAlbumFromId(albumId);
        return albumList;
    }

    public AlbumList getList() {
        return this.currentList;
    }

    private void loadUI() {
        setContentView(R.layout.activity_album_list);
        mainLayoutView = (RelativeLayout) findViewById(R.id.activity_album_list);
        setTitle(this.currentList.getName());
        this.toolbar = (Toolbar) findViewById(R.id.album_list_toolbar);
        setSupportActionBar(this.toolbar);
    }

    /**
     * TODO: This and createAlbumListView are pretty much duplicates of methods in SearchDialog - need to figure out how to put this in a common area
     */
    public void populateAlbumListView(List<Album> resultSet) {
        if (albumListView == null) {
            albumListView = createAlbumListView();
            this.mainLayoutView.addView(albumListView);
        }
        if (resultSet != null) {
            AlbumListArrayAdapter adapter = new AlbumListArrayAdapter(this, R.layout.album_list_item, resultSet);
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
     * Reload UI with any changes
     */
    @Override
    public void refreshListSettings() {
        loadUI();
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
        if(!super.onOptionsItemSelected(item)) {
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
