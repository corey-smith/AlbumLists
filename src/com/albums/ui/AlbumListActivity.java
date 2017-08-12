package com.albums.ui;

import com.albumlists.R;
import com.albums.model.AlbumList;
import com.albums.util.JsonUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AlbumListActivity extends Activity {
    AlbumList currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String currentListJson = intent.getStringExtra("com.albums.currentAlbumListJson");
        this.currentList = JsonUtil.getAlbumListFromJson(currentListJson);
        setContentView(R.layout.activity_album_list);
        TextView testView = (TextView) findViewById(R.id.list_name);
        testView.setText("AAAAAAAAAAAAAAAAH");
    }
}
