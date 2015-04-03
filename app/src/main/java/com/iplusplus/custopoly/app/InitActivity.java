package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.PlayerSkin;
import com.iplusplus.custopoly.model.ThemeHandler;
import com.iplusplus.custopoly.model.gamemodel.command.AskBuyCommand;
import com.iplusplus.custopoly.model.gamemodel.command.Command;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //Creates a default theme (Debugging)
        PlayerSkin vaderSkin = new PlayerSkin("vader",0,TOKEN_PATH + "vader.png");
        PlayerSkin meGustaSkin = new PlayerSkin("meGusta",0,TOKEN_PATH + "meGusta.png");
        HashSet<PlayerSkin> skins = new HashSet<PlayerSkin>();
        skins.add(vaderSkin);
        skins.add(meGustaSkin);
        GameTheme defaultTheme =  new GameTheme("default",0, THEME_PATH + "template/board.png",
                THEME_PATH + "template/communityBackground.png",THEME_PATH + "template/chanceBackground.png",skins);
        ThemeHandler.getInstance().switchThemeTo(defaultTheme);

        //Starts the Main Activity
        Intent act = new Intent(this, MainActivity.class);
        startActivity(act);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_init, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
