package com.albums.util;

import com.albums.model.AlbumList;
import com.google.gson.Gson;

public class JsonUtil {
    static Gson gson = new Gson();

    public static AlbumList getAlbumListFromJson(String albumListStr) {
        return gson.fromJson(albumListStr, AlbumList.class);
    }
}
