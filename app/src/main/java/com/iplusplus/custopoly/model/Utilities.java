package com.iplusplus.custopoly.model;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by fran on 19/04/2015.
 */
public class Utilities {
    /**
     * Convert Android's density independent pixels to pixels
     *
     * @param dp
     *            the amount of dps to convert
     * @param context
     *            a context
     * @return the conversion to pixels
     */
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        int px = Math.round(dp
                * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * Convert pixels to Android's density independent pixels
     *
     * @param px
     *            the amount of pixels to convert
     * @param context
     *            a context
     * @return the conversion to dps
     */
    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        int dp = Math.round(px
                / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}
