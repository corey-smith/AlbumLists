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
    private static int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }
    
    /**
     * Get a text color based on a background color
     * This is going to default to black or return white for dark backgrounds
     * @param rgb - background color as an int
     * @return - returnColor - black or white depending on background color
     */
    public static int getTextColorFromBackground(int rgb) {
        //default color to black, find individual rgb values
        int returnColor = 0xff000000;
        int red   = (rgb>>16) &0x0ff;
        int green = (rgb>> 8) &0x0ff;
        int blue  = (rgb)     &0x0ff;
        //convert color to gray and see if it's more white or black 
        //return white if color is darker than 50, which is an arbitrary number that seems to work
        if((red*0.299 + green*0.587 + blue*0.114) <= 50) {
            returnColor = 0xffffffff;
        }
        return returnColor;
    }
}
