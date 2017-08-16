package com.albums.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import android.graphics.drawable.Drawable;

/**
 * Object representation of an album
 * This currently represents most of the fields returned from a search but should eventually hold more
 */
public class Album {
    private String name;
    private String artist;
    private String url;
    transient private Drawable image = null;
    transient private int backgroundColor;
    transient private int textColor;
    @SerializedName("image")
    private List<AlbumImageURL> imageURLs = null;
    private String mbid;
    // These static final variables are what Last.fm differentiates image size
    public static final String IMAGE_SMALL = "small";
    public static final String IMAGE_MEDIUM = "medium";
    public static final String IMAGE_LARGE = "large";
    public static final String IMAGE_EXTRA_LARGE = "extralarge";

    public Album(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }
    
    public Drawable getImage() {
        return this.image;
    }
    
    public void setImage(Drawable image) {
        this.image = image;
    }
    
    public int getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public int getTextColor() {
        return this.textColor;
    }
    
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public List<AlbumImageURL> getImages() {
        return imageURLs;
    }

    public String getMbid() {
        return mbid;
    }
    
    @Override 
    public String toString() {
        return this.name + ": " + this.artist;
    }

    /**
     * Get album by image size.  
     * @param size - String value, should match one of the public static image size variables in this class
     * @return - Album corresponding to size. If none is found, return null. 
     */
    public AlbumImageURL getImageBySize(String size) {
        for(AlbumImageURL image : this.imageURLs) {
            if(image.getSize().equals(size)) {
                return image;
            }
        }
        return null;
    }

    /**
     * AlbumImage, this is just a size (corresponding to the static images in the outer album class) and a URL to the image
     */
    public class AlbumImageURL {
        @SerializedName("#text")
        private String ImageURL;
        @SerializedName("size")
        private String size;

        public String getImageURL() {
            return ImageURL;
        }

        public String getSize() {
            return size;
        }
        
        @Override 
        public String toString() {
            return this.ImageURL + ": " + this.size;
        }
    }
}
