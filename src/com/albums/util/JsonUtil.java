package com.albums.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.albums.model.AlbumList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Not currently in use, but will need to be used for serializing/deserializing metalis on opening/closing app
 */
public class JsonUtil {
    static Gson gson = new Gson();
    
    public static String metaListToString(List<AlbumList> metaList) {
        return gson.toJson(metaList);
    }
    
    public static List<AlbumList> metaListFromString(String metaListStr) {
        Type listType = new TypeToken<ArrayList<AlbumList>>(){}.getType();
        return gson.fromJson(metaListStr, listType);
    }
}
