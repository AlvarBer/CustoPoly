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
     * @param context the context of the application
     * @param resid the ID of the music you want to play
     */
    public static void play(Context context, int resid) {
        mediaPlayer = MediaPlayer.create(context, resid);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void pause() {
        mediaPlayer.pause();
    }

    public static void resume() {
        if (!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

}