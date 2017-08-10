package com.albums.util;

import java.io.InputStream;
import java.net.URL;
import com.albums.model.Album;
import com.albums.model.Album.AlbumImage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<Album, Void, Bitmap> {
    Context context;
    ImageView imageView;

    /**
     * Class to handle loading images on a separate thread
     * This calls out to a URL based on the album object, loads an image and then returns in to a callback method
     * @param context - current context
     */
    public ImageLoader(Context context, ImageView imageView) {
        this.context= context;
        this.imageView = imageView;
    }
    
    /**
     * Given an album, call an image URL (this just calls the Large Image) and return the Image as a Bitmap
     */
    @Override
    protected Bitmap doInBackground(Album... params) {
        Bitmap returnImg = null;
        Album album = (Album) params[0];
        AlbumImage albumImg = album.getImageBySize(Album.IMAGE_LARGE);
        String imageURL = albumImg.getImageURL();
        try {
            InputStream inputStream = (InputStream) new URL(imageURL).getContent();
            returnImg = BitmapFactory.decodeStream(inputStream);
            return returnImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Take the Bitmap Image, set the drawable on the List item, set the background of the view to the dominant color
     * This needs to be made more generic as more functionality is added
     */
    @Override
    protected void onPostExecute(Bitmap image) {
        if(image != null) {
            Drawable imageDrawable = new BitmapDrawable(context.getResources(), image);
            imageView.setImageDrawable(imageDrawable);
            int defaultColor = ColorUtil.getDominantColor(image);
            Palette palette = Palette.from(image).generate();
            int mutedColor = palette.getMutedColor(defaultColor);
            int vibrantColor = palette.getVibrantColor(mutedColor);
            View parentView = (View) imageView.getParent();
            parentView.setBackgroundColor(vibrantColor);
        }
    }
}
