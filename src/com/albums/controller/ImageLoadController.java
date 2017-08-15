package com.albums.controller;

import java.util.ArrayList;
import java.util.List;
import com.albums.model.Album;
import com.albums.ui.BaseAlbumActivity;
import com.albums.util.ImageLoader;

public class ImageLoadController {
    
    AlbumLoadable callbackClass;
    BaseAlbumActivity baseAlbumAcivity;
    List<Album> unprocessedAlbums;
    
    /**
     * Class used to initialize separate threads to find all of the album images in an album list
     * This should find all of the album images and then callback to the calling class once all are found
     * @param baseAlbumAcivity - current context
     * @param callbackClass - class to return too, must implement ImageLoadable
     */
    public ImageLoadController(BaseAlbumActivity baseAlbumAcivity, AlbumLoadable callbackClass) {
        this.baseAlbumAcivity = baseAlbumAcivity;
        this.callbackClass = callbackClass;
    }
    
    /**
     * Begin search, create/execute a new ImageLoader for each album
     * @param albums - album list to process
     */
    public void loadImages(List<Album> albums) {
        this.unprocessedAlbums = new ArrayList<Album>(albums);
        for(int i = 0; i < albums.size(); i++) {
            ImageLoader imageLoader = new ImageLoader(baseAlbumAcivity, this);
            imageLoader.execute(albums.get(i));
        }
    }
    
    /**
     * Callback method from ImageLoader, called for each image loaded
     * @param album - album that just had its image loaded, remove from unprocessed list
     */
    public void setAlbumProcessed(Album album) {
        unprocessedAlbums.remove(album);
        if(unprocessedAlbums.size() == 0) {
            callbackClass.populateAlbumListView();
        }
    }
    
}
