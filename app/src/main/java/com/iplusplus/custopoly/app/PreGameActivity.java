package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.PlayerSkin;
import com.iplusplus.custopoly.model.ThemeHandler;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.File;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        final GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();


        //Define behaviour of Play Button when is pressed
            final Button button = (Button) findViewById(R.id.playPreGameButton);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //Get the information of the players from the view
                    ArrayList<Player> players = getPlayers(theme.getPlayerSkinsList());

                    //Initiates a new game using the players introduced by the user
                    Game game = initGame(players,theme);
                    initCards(game,theme);

                    //Passes the information of the new game to the Game Activity

                    Intent myIntent = new Intent(PreGameActivity.this, GameActivity.class);
                    //TODO: Implement a way to pass a game to the Game Activity
                    /*myIntent.putExtra("game", game);  THIS DOESN'T WORK
                    *
                    * REASON:
                    * To pass information through a Bundle we need the information to be Serializable or Parceable.
                    * As game isn't any of this, is impossible to pass information that way. There are other mechanisms such as saving and loading the game in the cache,
                    * creating a singleton class for game data or make game serializable or parceable (I don't know how to do it)
                    *
                    * */
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
     * @param skins This parameter is NOT definitive. Just for getting the skins for the default players
     * @return A list of players that will participate in the game
     */
    private ArrayList<Player> getPlayers(HashSet<PlayerSkin> skins) {
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
