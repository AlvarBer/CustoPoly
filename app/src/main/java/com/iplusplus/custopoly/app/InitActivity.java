package com.iplusplus.custopoly.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.iplusplus.custopoly.model.MusicPlayer;

/**
 * Entry point of the app.
 *
 * This class takes care of loading resources and such.
 *
 * Once the resources are loaded, it automatically
 * switches to MainActivity.
 */
public class InitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //These will put the app on full screen
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_init);

        //Start music
        MusicPlayer.play(this, R.raw.rollin_at_5);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //Starts the Main Activity
                Intent mInHome = new Intent(InitActivity.this, MainActivity.class);
                startActivity(mInHome);

                finish();
            }
        }, 2000);
    }

    /**
     * We want to ignore the user back buttons press here because threads reasons
     */
    @Override
    public void onBackPressed() {
        //Do nothing
    }
}