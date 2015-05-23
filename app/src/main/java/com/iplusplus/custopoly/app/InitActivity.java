package com.iplusplus.custopoly.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.MusicPlayer;
import com.iplusplus.custopoly.model.PlayerSkin;
import com.iplusplus.custopoly.model.ThemeHandler;

import java.util.HashSet;


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
        //These will put the app on full screen
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //Creates a default theme (Debugging)
        PlayerSkin vaderSkin = new PlayerSkin("vader", 0, "placeholder_player_skin_star");
        PlayerSkin bullseyeSkin = new PlayerSkin("bullseye", 0, "placeholder_player_skin_bullseye");
        PlayerSkin queenSkin = new PlayerSkin("queen", 0, "placeholder_player_skin_queen");
        PlayerSkin kingSkin = new PlayerSkin("king", 0, "placeholder_player_skin_king");
        //TODO qutiar esto...
        PlayerSkin trollSkin = new PlayerSkin("troll", 0, "troll");
        PlayerSkin megustaSkin = new PlayerSkin("megusta", 0, "megusta");
        PlayerSkin trollfaceSkin = new PlayerSkin("trollface", 0, "trollface");
        HashSet<PlayerSkin> skins = new HashSet<PlayerSkin>();

        MusicPlayer.play(this, R.raw.rollin_at_5);

        skins.add(bullseyeSkin);
        skins.add(queenSkin);
        skins.add(kingSkin);
        skins.add(trollSkin);
        skins.add(megustaSkin);
        skins.add(trollfaceSkin);
        GameTheme defaultTheme =  new GameTheme("template",0, "template_board", "template_board","template_board", "template_board", "template_cards",skins);
        ThemeHandler.getInstance().addGameTheme(defaultTheme);

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

}

