package com.albums.util;

import java.io.InputStream;
import java.net.URL;
import com.albums.model.Album;
import com.albums.model.Album.AlbumImage;
import com.albums.ui.AlbumListArrayAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class ImageLoader extends AsyncTask<Album, Void, Drawable> {
    Context context;
    AlbumListArrayAdapter albumListArrayAdapter;

    public ImageLoader(Context context, AlbumListArrayAdapter albumListArrayAdapter) {
        this.context = context;
        this.albumListArrayAdapter = albumListArrayAdapter;
    }
    
    @Override
    protected Drawable doInBackground(Album... params) {
        Drawable returnImg = null;
        Album album = (Album) params[0];
        AlbumImage albumImg = album.getImageBySize(Album.IMAGE_SMALL);
        String imageURL = albumImg.getImageURL();
        try {
            InputStream inputStream = (InputStream) new URL(imageURL).getContent();
            returnImg = Drawable.createFromStream(inputStream, null);
            return returnImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    protected void onPostExecute(Drawable image) {
        albumListArrayAdapter.setAlbumImage(image);
    }
}
