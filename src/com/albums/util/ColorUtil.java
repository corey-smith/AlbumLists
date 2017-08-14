package com.albums.util;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

public class ColorUtil {
    /**
     * Get background color based on an image.
     * @param image - Bitmap image to find color for
     * @return - Background color based on Bitmap image
     */
    public static int getBackgroundColor(Bitmap image) {
        int defaultColor = getDominantColor(image);
        Palette palette = Palette.from(image).generate();
        int mutedColor = palette.getMutedColor(defaultColor);
        int vibrantColor = palette.getVibrantColor(mutedColor);
        return vibrantColor;
    }

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
