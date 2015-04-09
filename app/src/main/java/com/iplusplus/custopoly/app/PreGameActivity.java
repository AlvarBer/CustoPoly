package com.iplusplus.custopoly.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.iplusplus.custopoly.model.*;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

//TODO In all the activities.java the uppercase R doesn't work I have look on internet and it
//TODO says that is an Android Studio failure, but it builds ok.

//TODO: Should only allow to have player 3 after player 2 is on, and so on.

public class PreGameActivity extends ActionBarActivity implements View.OnClickListener {

    private CheckBox checkPlayer2,checkPlayer3,checkPlayer4;
    private Button bPlay,bCancel;
    private ViewFlipper flipperPlayer1, flipperPlayer2, flipperPlayer3, flipperPlayer4;
    private ArrayList<Player> players;
    private GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();
    //fIELDS UNDER COSNTRUCTION
    private ImageView imagePlayer1;
    private HashSet<PlayerSkin> skins = theme.getPlayerSkinsList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.players = new ArrayList<Player>();
        //TODO: Insert first player (always there)
        Player player1 = new Player(0, "Pepito", theme.getPlayerSkinsList().iterator().next().getImagePath());
        this.players.add(player1);

        setContentView(R.layout.activity_pre_game);
        //First I initialize all the items of the UI
        this.bPlay = (Button)findViewById(R.id.bPlay);
        this.bCancel = (Button)findViewById(R.id.bCancel);
        this.checkPlayer2 = (CheckBox)findViewById(R.id.checkPlayer2);
        this.checkPlayer3 = (CheckBox)findViewById(R.id.checkPlayer3);
        this.checkPlayer4 = (CheckBox)findViewById(R.id.checkPlayer4);
        this.flipperPlayer1 = (ViewFlipper)findViewById(R.id.FlipperPlayer1);
        this.flipperPlayer2 = (ViewFlipper)findViewById(R.id.FlipperPlayer2);
        this.flipperPlayer3 = (ViewFlipper)findViewById(R.id.FlipperPlayer3);
        this.flipperPlayer4 = (ViewFlipper)findViewById(R.id.FlipperPlayer4);
        //UNDER CONSTRUCTION
        //this.imagePlayer1 = (ImageView)findViewById(R.id.ImageForPlayer1);
        //Then I set the listeners for each item
        this.bCancel.setOnClickListener(this);
        this.bPlay.setOnClickListener(this);
        this.checkPlayer2.setOnClickListener(this);
        this.checkPlayer3.setOnClickListener(this);
        this.checkPlayer4.setOnClickListener(this);
        this.flipperPlayer1.setOnClickListener(this);
        this.flipperPlayer2.setOnClickListener(this);
        this.flipperPlayer3.setOnClickListener(this);
        this.flipperPlayer4.setOnClickListener(this);
        //UNDER CONSTRUCTION
        //this.imagePlayer1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {//function called from the click listener by default
        switch(v.getId()) {//the switch gets the id of the item clicked
            case R.id.bPlay:
                //Create and save game.
                GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();
                Game g = this.initGame(this.players, theme);
                this.initCards(g, theme);
                try {
                    SaveGameHandler.getInstance().saveGame(g);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Switch activities.
                Intent play = new Intent(PreGameActivity.this, GameActivity.class);
                startActivity(play);
                break;
            case R.id.bCancel:
                Intent cancel = new Intent(PreGameActivity.this, MainActivity.class);
                startActivity(cancel);
                break;
            case R.id.checkPlayer2:
                if (this.checkPlayer2.isChecked()) {
                    this.flipperPlayer2.setEnabled(true);
                    //TODO here we should call a method to enable player 2 in the game, the same
                    //TODO for players 3 and 4, in the following checkers
                    Player player2 = new Player(1, "Player2", this.theme.getPlayerSkinsList().iterator().next().getImagePath());
                    this.players.add(player2);
                    this.checkPlayer3.setEnabled(true);
                }
                else {
                    this.flipperPlayer2.setEnabled(false);
                    this.checkPlayer3.setEnabled(false);
                    this.checkPlayer4.setEnabled(false);
                    //We may need to remove this
                    for (int i = 1; i < this.players.size(); i++) {
                        this.players.remove(i);
                    }
                    //And substitude with
                    //this.players.remove(1);
                }
                break;
            case R.id.checkPlayer3:
                if(this.checkPlayer3.isChecked()) {
                    this.flipperPlayer3.setEnabled(true);
                    this.checkPlayer4.setEnabled(true);
                    Player player3 = new Player(2, "Player3", this.theme.getPlayerSkinsList().iterator().next().getImagePath());
                    this.players.add(player3);
                }
                else {
                    this.flipperPlayer3.setEnabled(false);
                    this.checkPlayer4.setEnabled(false);
                    for (int i = 2; i < this.players.size(); i++) {
                        this.players.remove(i);
                    }
                }
                break;
            case R.id.checkPlayer4:
                if(this.checkPlayer4.isChecked()) {
                    this.flipperPlayer4.setEnabled(true);
                    Player player4 = new Player(3, "Player4", this.theme.getPlayerSkinsList().iterator().next().getImagePath());
                    this.players.add(player4);
                }
                else {
                    this.flipperPlayer4.setEnabled(false);
                    this.players.remove(3);
                }
                break;
            case R.id.FlipperPlayer1://TODO: in each flipper is where you should change the skin you can do it by checking the id of the image
                    this.flipperPlayer1.showNext();
                break;
            case R.id.FlipperPlayer2:
                if(this.flipperPlayer2.isEnabled()) {
                    this.flipperPlayer2.showNext();
                }
                break;
            case R.id.FlipperPlayer3:
                if(this.flipperPlayer3.isEnabled()) {
                    this.flipperPlayer3.showNext();
                }
                break;
            case R.id.FlipperPlayer4:
                if(this.flipperPlayer4.isEnabled()) {
                    this.flipperPlayer4.showNext();
                }
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pre_game, menu);
        return true;
    }

    @Override//TODO this may be erased, is here because it could be needed
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
