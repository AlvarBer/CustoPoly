package com.iplusplus.custopoly.app;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.PlayerSkin;
import com.iplusplus.custopoly.model.SaveGameHandler;
import com.iplusplus.custopoly.model.ThemeHandler;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Corresponds with the player_selection_activity in the mockup.
 *
 * Allows to choose and start a game by selecting players and giving them names.
 * Maybe this can be done through settings?
 *
 * Access to the model is doe via ModelFacade.getInstance().[methodname]
 *  EXAMPLE:
 *          ModelFacade.getInstance().switchThemeTo(Themes.THEME1);
 */

public class PreGameActivity extends ActionBarActivity {

    //Attributes
    private TextView title;
    private Button play;
    private RelativeLayout layout;
    private HashSet<PlayerSkin> skins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        //Save the skins of the theme as an attribute
        final GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();
        this.skins = theme.getPlayerSkinsList();

        //Associate the components of the XML file to the class attributes
        this.layout = (RelativeLayout) findViewById(R.id.activity_pre_game_rl);
        this.play = (Button) findViewById(R.id.activity_pre_game_btn_play);
        this.title = (TextView) findViewById(R.id.activity_game_menu_tv_title);


        //Define behaviour of Play Button when is pressed
            play.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //Get the information of the players from the view
                    ArrayList<Player> players = getPlayers();

                    //Initiates a new game using the players introduced by the user
                    Game game = initGame(players,theme);
                    initCards(game,theme);

                    //Saves the information of the new game in the memory
                    try {
                        SaveGameHandler.getInstance().saveGame(game,"Game");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Initiates the  Game Activity
                    Intent myIntent = new Intent(PreGameActivity.this, GameActivity.class);
                    PreGameActivity.this.startActivity(myIntent);
                    finish();
                }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pre_game, menu);
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

    /**
     * Initializes a new game using a board linked to the current theme
     *
     * @param players The list of players that will be involved in the game
     * @param theme The current theme of the application
     * @return
     */
    private Game initGame(ArrayList<Player> players, GameTheme theme) {
        Board board = BoardFactory.readBoard(new File(theme.getBackgroundPath()));
        return new Game(players,board);
    }

    /**
     * Initializes the cards of the new game according to the current theme
     * @param game The new game that will be passed to the Game Activity
     * @param theme The current theme of the application
     */
    private void initCards(Game game,GameTheme theme) {
        Bank bank = game.getBank();
        bank.chanceCards = CardFactory.readChanceCards(new File(theme.getFortuneCardPath()));
        bank.communityCards = CardFactory.readCommunityCards(new File(theme.getCommunityBoxCardPath()));
    }

    /**
     * This function is in charge of collecting the information of the players of the game from the view.
     * Now is configured to return a list of default players just for DEBUGGING
     *
     * @return A list of players that will participate in the game
     */
    private ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        //Just for debugging, a default game is created with 2 players

        Iterator it = skins.iterator();
        PlayerSkin skin = (PlayerSkin) it.next();
        Player player1 = new Player(1,"Paco",skin.getImagePath());
        skin = (PlayerSkin) it.next();
        Player player2 = new Player(2,"Pepe",skin.getImagePath());
        players.add(player1);
        players.add(player2);

        return players;
    }

}
