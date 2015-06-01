package com.iplusplus.custopoly.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import android.widget.RadioGroup.LayoutParams;
import com.iplusplus.custopoly.model.*;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//TODO In all the activities.java the uppercase R doesn't work I have look on internet and it
//TODO says that is an Android Studio failure, but it builds ok.

//TODO: Should only allow to have player 3 after player 2 is on, and so on.

public class PreGameActivity extends Activity implements View.OnClickListener  {

    private CheckBox checkPlayer3,checkPlayer4;
    private Button bPlay,bCancel;
    private ViewFlipper flipperPlayer1, flipperPlayer2, flipperPlayer3, flipperPlayer4;
    private EditText Player1Name, Player2Name, Player3Name, Player4Name;
    private GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();
    private ArrayList<PlayerSkin> skins = ThemeHandler.getInstance().getUnlockedSkins();//I get the images from here

    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        setupViews();
        getSkinsView();
        setupListener();
    }

    private void setupViews()
    {
        //First I initialize all the items of the UI
        this.bPlay = (Button)findViewById(R.id.bPlay);
        this.bCancel = (Button)findViewById(R.id.bCancel);
        this.checkPlayer3 = (CheckBox)findViewById(R.id.checkPlayer3);
        this.checkPlayer4 = (CheckBox)findViewById(R.id.checkPlayer4);
        this.Player1Name = (EditText)findViewById(R.id.P1Name);
        this.Player2Name = (EditText)findViewById(R.id.P2Name);
        this.Player3Name = (EditText)findViewById(R.id.P3Name);
        this.Player4Name = (EditText)findViewById(R.id.P4Name);

        //here I initialize the flippers, see getSkinsView for more info
        this.flipperPlayer1 = (ViewFlipper)findViewById(R.id.FlipperPlayer1);
        this.flipperPlayer2 = (ViewFlipper)findViewById(R.id.FlipperPlayer2);
        this.flipperPlayer3 = (ViewFlipper)findViewById(R.id.FlipperPlayer3);
        this.flipperPlayer4 = (ViewFlipper)findViewById(R.id.FlipperPlayer4);

        //XML enable=false not working. We do it here by bruteforce
        this.flipperPlayer3.setEnabled(false);
        this.flipperPlayer4.setEnabled(false);

    }

    private void setupListener()
    {
        //UNDER CONSTRUCTION
        //this.imagePlayer1 = (ImageView)findViewById(R.id.ImageForPlayer1);
        //Then I set the listeners for each item
        this.bCancel.setOnClickListener(this);
        this.bPlay.setOnClickListener(this);
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
                ArrayList<Player> players = new ArrayList<Player>();

                ArrayList<PlayerSkin> skinsList = new ArrayList<>(skins);

                //Create and save game.
                // Initialize players
                //Insert first player (always there)
                Player player = new Player(0, this.Player1Name.getText().toString(), 0, skinsList.get(this.flipperPlayer1.getCurrentView().getId()));
                players.add(player);
                player = new Player(1, this.Player2Name.getText().toString(), 0,  skinsList.get(this.flipperPlayer2.getCurrentView().getId()));
                players.add(player);
                //Insert rest of players if it is required
                if(this.checkPlayer3.isChecked()) {
                    player = new Player(2, this.Player3Name.getText().toString(), 0, skinsList.get(this.flipperPlayer3.getCurrentView().getId()));
                    players.add(player);
                }
                if(this.checkPlayer4.isChecked()) {
                    player = new Player(3, this.Player4Name.getText().toString(), 0, skinsList.get(this.flipperPlayer4.getCurrentView().getId()));
                    players.add(player);
                }
                GameTheme theme = ThemeHandler.getInstance().getCurrentTheme();
                Game g = this.initGame(players, theme);

                try {
                    SaveGameHandler.getInstance().saveGame(g);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Switch activities.
                Intent play = new Intent(PreGameActivity.this, GameActivity.class);
                startActivity(play);
                finish();
                break;
            case R.id.bCancel:
                Intent cancel = new Intent(PreGameActivity.this, MainActivity.class);
                startActivity(cancel);
                break;
           /* case R.id.checkPlayer2:
               if (this.checkPlayer2.isChecked()) {
                    this.flipperPlayer2.setEnabled(true);
                    this.checkPlayer3.setEnabled(true);
                    this.Player2Name.setEnabled(true);
                }
                else {
                    this.flipperPlayer2.setEnabled(false);
                    this.checkPlayer3.setEnabled(false);
                    this.checkPlayer3.setChecked(false);
                    this.flipperPlayer3.setEnabled(false);
                    this.checkPlayer4.setEnabled(false);
                    this.checkPlayer4.setChecked(false);
                    this.flipperPlayer4.setEnabled(false);
                    this.Player2Name.setEnabled(false);
                    this.Player2Name.setText("Player2");
                }
                break;*/
            case R.id.checkPlayer3:
                if(this.checkPlayer3.isChecked()) {
                    this.flipperPlayer3.setEnabled(true);
                    this.checkPlayer4.setEnabled(true);
                    this.Player3Name.setEnabled(true);
                    Player player3 = new Player(2, "Player3", 0,  this.theme.getPlayerSkinsList().iterator().next());
                   }
                else {
                    this.flipperPlayer3.setEnabled(false);
                    this.Player3Name.setEnabled(false);
                    this.Player3Name.setText("Player3");
                    this.checkPlayer4.setEnabled(false);
                    this.checkPlayer4.setChecked(false);
                    this.flipperPlayer4.setEnabled(false);
                }
                break;
            case R.id.checkPlayer4:
                if(this.checkPlayer4.isChecked()) {
                    this.flipperPlayer4.setEnabled(true);
                    this.Player4Name.setEnabled(true);
                    Player player4 = new Player(3, "Player4", 0 , this.theme.getPlayerSkinsList().iterator().next());
                }
                else {
                    this.flipperPlayer4.setEnabled(false);
                    this.Player4Name.setEnabled(false);
                    this.Player4Name.setText("Player4");
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

    /**
     * Initializes a new game using a board linked to the current theme
     *
     * @param players The list of players that will be involved in the game
     * @param theme The current theme of the application
     * @return
     */
    private Game initGame(ArrayList<Player> players, GameTheme theme) {
        Board board = BoardFactory.readBoard(getResources().openRawResource(Utilities.getResId(theme.getBackgroundPathResource(),R.raw.class)));
        Game newGame = new Game(players, board, theme);
        initCards(newGame, theme);
        return newGame;
    }

    /**
     * Initializes the cards of the new game according to the current theme
     * @param game The new game that will be passed to the Game Activity
     * @param theme The current theme of the application
     */
    private void initCards(Game game, GameTheme theme) {
        Bank bank = game.getBank();
        bank.chanceCards = CardFactory.readChanceCards(getResources().openRawResource(Utilities.getResId(theme.getCardsDataPath(),R.raw.class)));
        bank.communityCards = CardFactory.readCommunityCards(getResources().openRawResource(Utilities.getResId(theme.getCardsDataPath(),R.raw.class)));
    }

    /**
     * This function is in charge of collecting the information of the players of the game from the view.
     * Now is configured to return a list of default players just for DEBUGGING
     *
     * @return A list of players that will participate in the game
     * thia is now implemented in the OnClick in the case R.id.bPlay
     */
   /* private ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        //Just for debugging, a default game is created with 2 players
        Iterator it = this.skins.iterator();
        PlayerSkin skin = (PlayerSkin) it.next();
        Player player1 = new Player(1,"Paco",skin.getImagePath());
        skin = (PlayerSkin) it.next();
        Player player2 = new Player(2,"Pepe",skin.getImagePath());
        players.add(player1);
        players.add(player2);

        return players;
    }*/

    private void getSkinsView() { // here I add the images in the arrayList so I can put them in the flippers;
        Iterator it = this.skins.iterator();
        int count = 0;
        ImageView skinImage1, skinImage2, skinImage3, skinImage4;

        while (it.hasNext()) {
            PlayerSkin skin = (PlayerSkin) it.next();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT,
                                                                              LayoutParams.MATCH_PARENT);
            skinImage1 = createImage(count, params, Utilities.getResId(skin.getImageResourceName(),R.drawable.class));
            skinImage2 = createImage(count, params, Utilities.getResId(skin.getImageResourceName(),R.drawable.class));
            skinImage3 = createImage(count, params, Utilities.getResId(skin.getImageResourceName(),R.drawable.class));
            skinImage4 = createImage(count, params, Utilities.getResId(skin.getImageResourceName(),R.drawable.class));
            this.flipperPlayer1.addView(skinImage1, count);
            this.flipperPlayer2.addView(skinImage2, count);
            this.flipperPlayer3.addView(skinImage3, count);
            this.flipperPlayer4.addView(skinImage4, count);
            count++;
        }
    }

    private ImageView createImage(int count, LinearLayout.LayoutParams params, int imageResId) {
        ImageView image = new ImageView(this);
        image.setLayoutParams(params);//I set wrap_content to the layout params
        image.setId(count);//I set the id from 0 to numImages - 1, I don't know how to put a string (es muy oscuro jeje)
        image.setImageResource(imageResId);
        return image;
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(PreGameActivity.this, GameMenuActivity.class);
        startActivity(back);
        PreGameActivity.this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onPause();
        MusicPlayer.resume();
    }
}
