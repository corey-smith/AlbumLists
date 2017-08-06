package com.albums.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Album {
    private String name;
    private String artist;
    private String url;
    @SerializedName("image")
    private List<AlbumImage> images = null;
    private String mbid;

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public List<AlbumImage> getImages() {
        return images;
    }

    public String getMbid() {
        return mbid;
    }

    public class AlbumImage {
        @SerializedName("#text")
        private String text;
        @SerializedName("size")
        private String size;

        public String getText() {
            return text;
        }

        public String getSize() {
            return size;
        }
    }
}
