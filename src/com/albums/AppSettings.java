package com.albums;

import java.util.HashMap;
import com.albumlists.R;
import android.content.Context;

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
