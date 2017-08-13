package com.albums.ui;

import java.util.List;
import com.albumlists.R;
import com.albums.model.Album;
import com.albums.model.AlbumList;
import com.albums.util.JsonUtil;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
        String currentListJson = intent.getStringExtra("com.albums.currentAlbumListJson");
        return JsonUtil.getAlbumListFromJson(currentListJson);
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
     * Create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.album_list_menu, menu);
        return true;
    }
}
