package com.albums.util;

import com.albums.model.AlbumList;
import com.google.gson.Gson;

/**
 * Not currently in use, but will need to be used for serializing/deserializing metalis on opening/closing app
 */
public class JsonUtil {
    static Gson gson = new Gson();

    public static AlbumList getAlbumListFromJson(String albumListStr) {
        return gson.fromJson(albumListStr, AlbumList.class);
    }
}
