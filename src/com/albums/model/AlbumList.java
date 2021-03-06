package com.albums.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AlbumList implements Listable {
    List<Album> albums;
    String name;
    Date dateCreated;
    UUID id;

    /**
     * This constructor may need to be changed at some point
     */
    public AlbumList(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public List<Album> getAlbums() {
        return this.albums;
    }

    public String getName() {
        return this.name;
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void add(Album album) {
        if(this.albums == null) this.albums = new ArrayList<Album>();
        this.albums.add(album);
    }
    
    public void remove(Album album) {
        this.albums.remove(album);
    }
    
    public int size() {
        return this.albums.size();
    }

    public Album get(int position) {
        return this.albums.get(position);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
