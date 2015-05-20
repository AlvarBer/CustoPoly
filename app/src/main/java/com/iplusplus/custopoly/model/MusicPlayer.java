package com.iplusplus.custopoly.model;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Singleton Class that allows us to play any music clip/song
 * Created by Mortadelegle on 20/05/2015.
 */

public class MusicPlayer {
    //Fields
    private static MediaPlayer mediaPlayer;


    //Functions
    /**
     * If you don't know the context try getActivity().getApplicationContext()
     *
     * @param context
     * @param resid
     */
    public static void play(Context context, int resid) {
        mediaPlayer = MediaPlayer.create(context, resid);
        mediaPlayer.start();
    }
}