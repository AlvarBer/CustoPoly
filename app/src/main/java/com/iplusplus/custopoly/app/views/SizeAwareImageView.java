package com.iplusplus.custopoly.app.views;


import android.widget.ImageView;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.util.Log;
/**
 * Created by usuario_local on 30/04/2015.
 */
public class SizeAwareImageView extends ImageView {

    private int widthOnScreen;
    private int heighthOnScreen;

    public SizeAwareImageView(Context context) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Get image matrix values and place them in an array
        float[] f = new float[9];
        getImageMatrix().getValues(f);

        // Extract the scale values using the constants (if aspect ratio maintained, scaleX == scaleY)
        final float scaleX = f[Matrix.MSCALE_X];
        final float scaleY = f[Matrix.MSCALE_Y];

        // Get the drawable (could also get the bitmap behind the drawable and getWidth/getHeight)
        final Drawable d = getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

        // Calculate the actual dimensions
        final int actW = Math.round(origW * scaleX);
        final int actH = Math.round(origH * scaleY);

        widthOnScreen = actW;
        heighthOnScreen = actH;

        Log.e("DBG", "["+origW+","+origH+"] -> ["+actW+","+actH+"] & scales: x="+scaleX+" y="+scaleY);
    }

    public int getWidthOnScreen(){return widthOnScreen;}
    public int getHeighthOnScreen(){return heighthOnScreen;}

}
