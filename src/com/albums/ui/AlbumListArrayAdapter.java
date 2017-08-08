package com.albums.ui;

import java.util.List;
import com.albumlists.R;
import com.albums.model.Album;
import com.albums.util.ImageLoader;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * UI logic for AlbumList items
 * TODO: Need to break up some of these methods, and move all non-ui logic to a controller class
 */
public class AlbumListArrayAdapter extends ArrayAdapter<Album> {
    Context context;

    TextView titleTextView;
    TextView artistTextView;
    ImageView albumImageView;
    List<Album> albums;
    ImageLoader albumArrayListAdapterController;

    public AlbumListArrayAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
        albumArrayListAdapterController = new ImageLoader(context, this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Album currentAlbum = this.albums.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.album_list_item, parent, false);
            titleTextView = (TextView) convertView.findViewById(R.id.album_list_item_title);
            artistTextView = (TextView) convertView.findViewById(R.id.album_list_item_artist);
            albumImageView = (ImageView) convertView.findViewById(R.id.album_list_item_image);
            titleTextView.setText(currentAlbum.getName());
            artistTextView.setText(currentAlbum.getArtist());
            albumArrayListAdapterController.getImageDrawable(currentAlbum);
        }
        return convertView;
    }
    
    public void setAlbumImage(Drawable albumImage) {
        albumImageView.setImageDrawable(albumImage);
    }
}
