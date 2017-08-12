package com.albums.ui;

import com.albumlists.R;
import com.albums.model.AlbumList;
import com.albums.util.JsonUtil;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AlbumListActivity extends BaseAlbumActivity {
    AlbumList currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String currentListJson = intent.getStringExtra("com.albums.currentAlbumListJson");
        this.currentList = JsonUtil.getAlbumListFromJson(currentListJson);
        setContentView(R.layout.activity_album_list);
        setTitle(this.currentList.getName());
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.album_list_toolbar);
        setSupportActionBar(mainToolbar);
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
}
