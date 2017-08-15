package com.albums.controller;

import java.util.ArrayList;
import java.util.List;
import com.albums.model.Album;
import com.albums.ui.BaseAlbumActivity;
import com.albums.util.ImageLoader;

public class AlbumLoader {
    
    AlbumLoadable callbackLoadable;
    BaseAlbumActivity baseAlbumActivity;
    List<Album> unprocessedAlbums;
    
    public AlbumLoader(BaseAlbumActivity baseAlbumActivity, AlbumLoadable callbackLoadable) {
        this.baseAlbumActivity = baseAlbumActivity;
        this.callbackLoadable = callbackLoadable;
    }

    /**
     * Begin search, create/execute a new ImageLoader for each album
     * @param albums - album list to process
     */
    public void loadImages(List<Album> albums) {
        if (albums != null) {
            this.unprocessedAlbums = new ArrayList<Album>(albums);
            for (int i = 0; i < albums.size(); i++) {
                ImageLoader imageLoader = new ImageLoader(baseAlbumActivity, this);
                imageLoader.execute(albums.get(i));
            }
        }
    }

    /**
     * Callback method from ImageLoader, called for each image loaded
     * @param album - album that just had its image loaded, remove from unprocessed list
     */
    public void setAlbumProcessed(Album album) {
        unprocessedAlbums.remove(album);
        if (unprocessedAlbums.size() == 0) {
            callbackLoadable.populateAlbumListView();
        }
    }
    
}
