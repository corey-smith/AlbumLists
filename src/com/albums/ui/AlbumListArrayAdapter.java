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
        final ViewHolder viewHolder;
        if (itemView == null) {
            viewHolder = new ViewHolder();
            itemView = buildView(itemView, parent);
            viewHolder.titleText = (TextView) itemView.findViewById(R.id.album_list_item_title);
            viewHolder.artistText = (TextView) itemView.findViewById(R.id.album_list_item_artist);
            viewHolder.imageView = (ImageView) itemView.findViewById(R.id.album_list_item_image);
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }
        Album currentAlbum = this.albums.get(position);
        viewHolder.titleText.setText(currentAlbum.getName());
        viewHolder.artistText.setText(currentAlbum.getArtist());
        new ImageLoader(context, viewHolder.imageView).execute(currentAlbum);
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
        return itemView;
    }
    
    private class ViewHolder {
        private TextView titleText;
        private TextView artistText;
        private ImageView imageView;
    }
}
