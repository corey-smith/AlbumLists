package com.albums.ui;

import com.albumlists.R;
import com.albums.model.AlbumList;
import com.albums.util.JsonUtil;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class AlbumListActivity extends AppCompatActivity {
    AlbumList currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String currentListJson = intent.getStringExtra("com.albums.currentAlbumListJson");
        this.currentList = JsonUtil.getAlbumListFromJson(currentListJson);
        setContentView(R.layout.activity_album_list);
        TextView testView = (TextView) findViewById(R.id.list_name);
        testView.setText(this.currentList.getName());
        // get the toolbar out of main.xml and set it as the actionbar for the app
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
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
