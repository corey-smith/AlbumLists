package com.albums.util;

import java.io.InputStream;
import java.net.URL;
import com.albums.controller.AlbumLoader;
import com.albums.model.Album;
import com.albums.model.Album.AlbumImageURL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<Album, Void, Album> {
    Context context;
    ImageView imageView;
    AlbumLoader albumLoader;

    /**
     * Class to handle loading images on a separate thread
     * This calls out to a URL based on the album object, loads an image and then returns in to a callback method
     * @param context - current context
     */
    public ImageLoader(Context context, AlbumLoader albumLoader) {
        this.context= context;
        this.albumLoader = albumLoader;
    }
    
    /**
     * Given an album, call an image URL (this just calls the Large Image) and return the Image as a Bitmap
     */
    @Override
    protected Album doInBackground(Album... params) {
        Bitmap returnImg = null;
        Album album = (Album) params[0];
        AlbumImageURL albumImg = album.getImageBySize(Album.IMAGE_LARGE);
        String imageURL = albumImg.getImageURL();
        try {
            InputStream inputStream = (InputStream) new URL(imageURL).getContent();
            returnImg = BitmapFactory.decodeStream(inputStream);
            album.setImage(new BitmapDrawable(context.getResources(), returnImg));
            int backgroundColor = ColorUtil.getBackgroundColor(returnImg);
            int textColor = ColorUtil.getTextColorFromBackground(backgroundColor);
            album.setBackgroundColor(backgroundColor);
            album.setTextColor(textColor);
            return album;
        } catch (Exception e) {
            Log.e("AlbumsList", "Error loading image for " + album.toString(), e);
            album.setBackgroundColor(0xffffffff);
            album.setTextColor(0xff000000);
        }
        return album;
    }
    
    /**
     * Take the Bitmap Image, set the drawable on the List item, set the background of the view to the dominant color
     * This needs to be made more generic as more functionality is added
     */
    
    @Override
    protected void onPostExecute(Album album) {
        albumLoader.setAlbumProcessed(album);
    }
}
