package com.albums.controller;

/**
 * Should be implemented by classes that load album lists
 * Most list info can be loaded from json, but actual images need API calls
 * This interface is to ensure when albums are loaded, the API calls are made uniformly
 * This is also to make sure all images are loaded before trying to display
 */
public interface AlbumLoadable {
    public void populateAlbumListView();
}
