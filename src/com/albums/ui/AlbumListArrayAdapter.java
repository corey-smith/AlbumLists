package com.albums.ui;

import java.util.List;
import com.albumlists.R;
import com.albums.model.Album;
import com.albums.util.ImageLoader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * UI logic for AlbumList items
 */
public class AlbumListArrayAdapter extends ArrayAdapter<Album> {
    Context context;
    TextView titleTextView;
    TextView artistTextView;
    ImageView albumImageView;
    List<Album> albums;

    public AlbumListArrayAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
    }

    /**
     * Create View for item in ArrayList
     * This includes a call to build out the view's components and then populate the view
     */
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if (itemView == null) {
            itemView = buildView(itemView, parent);
            Album currentAlbum = this.albums.get(position);
            this.titleTextView.setText(currentAlbum.getName());
            //this.artistTextView.setText(currentAlbum.getArtist());
            //new ImageLoader(context, this.albumImageView).execute(currentAlbum);
        }
        return itemView;
    }
    
    /**
     * Inflate view, set instance variables for the fields in the view
     * @param itemView - current item View
     * @param parent - parent ViewGroup
     * @return - built out (but unpopulated) view
     */
    private View buildView(View itemView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.album_list_item, parent, false);
        titleTextView = (TextView) itemView.findViewById(R.id.album_list_item_title);
        artistTextView = (TextView) itemView.findViewById(R.id.album_list_item_artist);
        albumImageView = (ImageView) itemView.findViewById(R.id.album_list_item_image);
        return itemView;
    }
}
