package com.albums.model;

import java.util.Date;
import java.util.List;
import com.google.gson.Gson;

public class AlbumList {
    List<Album> albums;
    String name;
    Date dateCreated;

    /**
     * This constructor may need to be changed at some point
     */
    public AlbumList(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return this.albums;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String asJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
