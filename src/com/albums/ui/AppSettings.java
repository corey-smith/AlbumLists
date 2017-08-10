package com.albums.ui;

import java.util.HashMap;
import com.albumlists.R;
import android.content.Context;

/**
 * This is going to be needed at some point to store global system settings
 * This will also need to save/load these values somehow
 * This is basically going to be a wrapper for a HashMap
 */
public class AppSettings {
    static Context context;

    public static void initialize(Context newContext) {
        AppSettings.context = newContext;
    }

    /**
     * Base URL values map holding static values This should be appended to for specific calls
     */
    public static HashMap<String, String> getBaseValuesMap() {
        HashMap<String, String> baseValues = new HashMap<String, String>();
        baseValues.put("api_key", context.getString(R.string.api_key));
        return baseValues;
    }
}
