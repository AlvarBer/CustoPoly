package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import com.iplusplus.custopoly.model.GameTheme;
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

public class InitActivity extends ActionBarActivity {
    //Constants
    protected static final String THEME_PATH = "res/drawable/themes/";
    protected static final String TOKEN_PATH = "res/drawable/tokens/";
    protected static final String DATA_PATH = "res/gameData/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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

        skins.add(bullseyeSkin);
        skins.add(queenSkin);
        skins.add(kingSkin);
        skins.add(trollSkin);
        skins.add(megustaSkin);
        skins.add(trollfaceSkin);
        GameTheme defaultTheme =  new GameTheme("default",0, "template_board", "template_board","template_board", DATA_PATH + "template_board.txt", DATA_PATH + "template_cards.txt",skins);
        ThemeHandler.getInstance().switchThemeTo(defaultTheme);

        //Starts the Main Activity
        Intent act = new Intent(this, MainActivity.class);
        startActivity(act);
        finish();
    }
}
