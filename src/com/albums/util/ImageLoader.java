package com.albums.util;

import java.io.InputStream;
import java.net.URL;
import com.albumlists.R;
import com.albums.model.Album;
import com.albums.model.Album.AlbumImage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<Album, Void, Bitmap> {
    Context context;
    View itemView;

    /**
     * Class to handle loading images on a separate thread
     * This calls out to a URL based on the album object, loads an image and then returns in to a callback method
     * @param context - current context
     */
    public ImageLoader(Context context, View itemView) {
        this.context= context;
        this.itemView = itemView;
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
        ImageView albumImageView = (ImageView) itemView.findViewById(R.id.album_list_item_image);
        Drawable imageDrawable = new BitmapDrawable(context.getResources(), image);
        albumImageView.setImageDrawable(imageDrawable);
        int dominantColor = ColorUtil.getDominantColor(image);
        itemView.setBackgroundColor(dominantColor);
    }
}
