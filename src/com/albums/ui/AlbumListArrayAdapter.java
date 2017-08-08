package com.albums.ui;

import java.util.List;
import com.albums.model.Album;
import android.content.Context;
import android.widget.ArrayAdapter;

public class AlbumListArrayAdapter<Album> extends ArrayAdapter<Album> {

    public AlbumListArrayAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
    }
}
