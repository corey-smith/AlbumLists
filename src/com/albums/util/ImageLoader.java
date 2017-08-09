package com.albums.util;

import java.io.InputStream;
import java.net.URL;
import com.albums.model.Album;
import com.albums.model.Album.AlbumImage;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<Album, Void, Drawable> {
    ImageView albumImageView;

    public ImageLoader(ImageView albumImageView) {
        this.albumImageView = albumImageView;
    }
    
    @Override
    protected Drawable doInBackground(Album... params) {
        Drawable returnImg = null;
        Album album = (Album) params[0];
        AlbumImage albumImg = album.getImageBySize(Album.IMAGE_LARGE);
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
        albumImageView.setImageDrawable(image);
    }
}
