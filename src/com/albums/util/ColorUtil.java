package com.albums.util;

import android.graphics.Bitmap;

public class ColorUtil {
    
    /**
     * This way of getting the dominant color doesn't work great, but it works okay and fast
     * @param bitmap
     * @return dominant color as int
     */
    public static int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }
    
}
