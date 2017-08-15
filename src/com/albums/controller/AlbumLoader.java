package com.albums.controller;

import java.util.List;
import com.albums.model.Album;
import com.albums.ui.BaseAlbumActivity;

public class AlbumLoader {
    
    AlbumLoadable callbackLoadable;
    ImageLoadController imageLoadController;
    BaseAlbumActivity baseAlbumActivity;
    
    public AlbumLoader(BaseAlbumActivity baseAlbumActivity, AlbumLoadable callbackLoadable) {
        this.baseAlbumActivity = baseAlbumActivity;
        this.callbackLoadable = callbackLoadable;
    }
    
    /**
     * Initialize ImageLoadController instance and execute to load all album images
     */
    public void loadImages(List<Album> albums) {
        this.imageLoadController = new ImageLoadController(baseAlbumActivity, callbackLoadable);
        this.imageLoadController.loadImages(albums);
    }
    
}
