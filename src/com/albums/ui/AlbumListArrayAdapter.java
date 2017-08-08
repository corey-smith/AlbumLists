package com.albums.ui;

import java.util.List;
import com.albumlists.R;
import com.albums.model.Album;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AlbumListArrayAdapter extends ArrayAdapter<Album> {
    Context context;
    List<Album> albums;

    public AlbumListArrayAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.album_list_item, parent, false);
            TextView titleText = (TextView) convertView.findViewById(R.id.album_list_item_title);
            TextView artistText = (TextView) convertView.findViewById(R.id.album_list_item_artist);
            //TODO: remove the \n below here and handle it through the view xml file
            titleText.setText(this.albums.get(position).getName());
            artistText.setText(this.albums.get(position).getArtist() + "\n");
        }
        return convertView;
    }
}
