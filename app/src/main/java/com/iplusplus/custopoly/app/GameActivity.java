package com.iplusplus.custopoly.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.SaveGameHandler;
import com.iplusplus.custopoly.model.Utilities;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

import java.io.IOException;

/**
 * Corresponds with the game_activity in the mockup.
 *
 * Allows to play the entire game, from start to finish.
 *
 * Access to the model is doe via ModelFacade.getInstance().[methodname]
 *  EXAMPLE:
 *          ModelFacade.getInstance().switchThemeTo(Themes.THEME1);
 */

public class GameActivity extends ActionBarActivity {

    //Attributes
    private Game game;
    private ImageView boardBackground;
    private FrameLayout players;

    //Constants
    private static final int SPACE_WIDTH = 390;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //TODO:Repair the system of loading and saving the game
        //Loads the information of the new game from the memory
        try {
            this.game = SaveGameHandler.getInstance().loadGame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setupViews();
        drawBoard();
        drawPlayers();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    private void drawPlayers() {
        LayoutInflater inflater = getLayoutInflater();
        int i = 0;
        for (Player player : game.getPlayers()) {
            View view = inflater.inflate(R.layout.player, players,false);
            ImageView skin = (ImageView) view.findViewById(R.id.player_iv_skin);
            skin.setImageResource(getResources().getIdentifier(player.getSkin().getImageResourceName(), "drawable", getPackageName()));
            view.setTag(i);
            view.setX(Utilities.dpToPx(
                    (int) (SPACE_WIDTH * (player.getLandIndex() + 0.5)), this)
                    + calculateSpaceRelativePositionX(player));
            view.setY(calculatePositionY(player));
            players.addView(view);

            i++;
        }
    }

    private void drawBoard() {
        this.boardBackground.setImageResource(getResources().getIdentifier(game.getTheme().getBackgroundPathResource(), "drawable", getPackageName()));
    }

    private void setupViews() {
        this.boardBackground = (ImageView) findViewById(R.id.activity_game_iv_boardBackground);
        this.players = (FrameLayout)findViewById(R.id.activity_game_fl_players);
    }

    /**
     * Utility function for all functions related to moving the player views.
     * Calculates the X position of a player relative to the center of his
     * current space, so that the players don't all sit on the same spot.
     *
     * @param player
     *            the player to calculate the position for
     * @return X position relative to the center of the current space, in pixels
     */
    private int calculateSpaceRelativePositionX(Player player) {
        return Utilities.dpToPx((player.getPlayerID() % 2 == 0 ? 1 : -1) * 12,
                this);
    }

    /**
     * Utility function for all functions related to moving the player views.
     * Calculates the Y position of a player, so that the players don't all sit
     * on the same spot.
     *
     * @param player
     *            the player to calculate the position for
     * @return Y position in pixels
     */
    private int calculatePositionY(Player player) {
        return Utilities.dpToPx(146 + 22 * (player.getPlayerID() / 2), this);
    }


}
