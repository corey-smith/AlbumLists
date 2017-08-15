package com.albums.controller;

import java.util.ArrayList;
import java.util.List;
import com.albums.model.Album;
import com.albums.ui.BaseAlbumActivity;
import com.albums.util.ImageLoader;

public class ImageLoadController {
    
    ImageLoadable callbackClass;
    BaseAlbumActivity baseAlbumAcivity;
    List<Album> unprocessedAlbums;
    
    public ImageLoadController(BaseAlbumActivity baseAlbumAcivity, ImageLoadable callbackClass) {
        this.baseAlbumAcivity = baseAlbumAcivity;
        this.callbackClass = callbackClass;
    }
    
    public void loadImages(List<Album> albums) {
        if(albums.size() > 0) {
            this.unprocessedAlbums = new ArrayList<Album>(albums);
            ImageLoader imageLoader = new ImageLoader(baseAlbumAcivity, this);
            imageLoader.execute(albums.get(0));
        }
    }
    
    public void setAlbumProcessed(Album album) {
        unprocessedAlbums.remove(album);
        ImageLoader imageLoader = new ImageLoader(baseAlbumAcivity, this);
        System.out.println("ALBUMS LEFT: " + unprocessedAlbums.size());
        if(unprocessedAlbums.size() > 0) {
            imageLoader.execute(unprocessedAlbums.get(0));
        } else {
            callbackClass.populateAlbumListView();
        }
    }
    
}
