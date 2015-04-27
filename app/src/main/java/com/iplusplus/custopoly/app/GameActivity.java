package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.iplusplus.custopoly.model.SaveGameHandler;
import com.iplusplus.custopoly.model.Utilities;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Corresponds with the game_activity in the mockup.
 * Allows to play a game, displaying it on the screen. In addition, it allows the communication
   with the game through a system of buttons
 */

public class GameActivity extends ActionBarActivity {

    //Attributes
    private GameFacade game;
    private ImageView boardBackground;
    private FrameLayout players;
    private HashMap<Integer,Square> squarePositions;

    //Constants
    private static final double POS_X_START = 196;
    private static final double POS_Y_START = 158;
    private static final double SMALL_SMALL_H_DISTANCE = 36.5;
    private static final double SMALL_SMALL_V_DISTANCE = 29.5;
    private static final double BIG_SMALL_H_DISTANCE = 50;
    private static final double BIG_SMALL_V_DISTANCE = 40;


	/**
	* Called when GameActivity is created. It's in charge of creating the activity, loading
	* the game and initializing all the visual components of the view and display them on 
	* the screen
	*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
		
        loadGame();
        setupViews();
        initSquarePositions();
        drawBoard();
        drawPlayers();
		drawMoney();
    }

    /**
     * Called when the GameActivity is hidden. Automatically saves the game.
     *
     * @see android.app.Activity#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        saveGame();
    }

    /**
     * Called every time the app is resumed. Loads the game and draw the board
     * and the players
     *
     * @see android.app.Activity#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
        loadGame();
        drawBoard();
        drawPlayers();
		drawMoney();
    }

    /**
     * Defines the behaviour of the back button.
     * It shows a dialog asking the user to confirm if he wants to quit the game
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.ingame_quitgame_title))
                .setMessage(getString(R.string.ingame_quitgame_message))
                .setPositiveButton(getString(R.string.ingame_buyyesbutton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent play = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(play);
                    }

                })
                .setNegativeButton(getString(R.string.ingame_buynobutton), null)
                .show();
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

    /**
     * Displays the skins of the players according to their current position 
	 * in the board. If there are more than one player in the same square, it 
	 * spreads the players to the border of the square to fit them in them.
	 * Each player is a view that is include in a FrameLayout (players).
     */
    private void drawPlayers() {
		
        LayoutInflater inflater = getLayoutInflater();
        int i = 0;
        float x, y;
        Square sqPos;
		
		//The following process is repeated for each player in the game
        for (Player player : game.getPlayers()) {
		
			//Gets the position (Square) of the square in which the player is from the map of positions
            sqPos = squarePositions.get(player.getLandIndex());
			
            View view = inflater.inflate(R.layout.player, players,false);
			
			//Set the size of the skin
            view.setScaleX((float) 0.5);
            view.setScaleY((float) 0.5);
			
			//Recover the imageView that is in Player.xml (Static layout) and set the skin of the player
            ImageView skin = (ImageView) view.findViewById(R.id.player_iv_skin);
            skin.setImageResource(getResources().getIdentifier(player.getSkin().getImageResourceName(), "drawable", getPackageName()));
            view.setTag(i);

			//Defines the position of the player in the center of the square
            x = Utilities.dpToPx((int) (sqPos.getX()), this);
            y = Utilities.dpToPx((int) (sqPos.getY()), this);

			//If the position is already occupied by another player, spreads the player to the border of the square
            if (isSharedSquare(game.getPlayers(), player.getLandIndex())) {
                x += calculateSpaceRelativePositionX(player, sqPos);
                y += calculateSpaceRelativePositionY(player, sqPos);
            }
			
			//Set the position of the player and add it to view 
			//TODO: Here we must set the position according to the center of the board, not the activity. ex: view.setX(players.getX() + x);
            view.setX(x);
            view.setY(y);		
            players.addView(view);
            i++;
        }
    }

    private boolean isSharedSquare(ArrayList<Player> players, int landIndex) {
        int i = 0;
        for (Player p:players) {
            if (p.getLandIndex() == landIndex)
                i++;
        }
        return i > 1;
    }

    /**
     * Displays the board of the theme on the image of the board
     */
    private void drawBoard() {
        this.boardBackground.setImageResource(getResources().getIdentifier(game.getTheme().getBackgroundPathResource(), "drawable", getPackageName()));
    }

    /**
     * Initializes all the components of the view
     */
    private void setupViews() {
        this.boardBackground = (ImageView) findViewById(R.id.activity_game_iv_boardBackground);
        this.players = (FrameLayout)findViewById(R.id.activity_game_fl_players);
    }

	/**
	* Initializes the coordinates, size and position of each square of the map
	*/
    private void initSquarePositions() {
        squarePositions = new HashMap<Integer,Square>();

        //Initializes the squares of the bottom
        Square bigSquare = new Square(POS_X_START,POS_Y_START,Position.DOWN,Size.BIG);
        squarePositions.put(0,bigSquare);
        double posXSmall = POS_X_START-BIG_SMALL_H_DISTANCE;
        double posYSmall = POS_Y_START;
        Square smallSquare;

        for (int i = 1; i < 10; i++) {
            smallSquare = new Square(posXSmall, posYSmall, Position.DOWN,Size.SMALL);
            if (i < 9)
                posXSmall -= SMALL_SMALL_H_DISTANCE;
            squarePositions.put(i,smallSquare);
        }

        //Initializes Jail Square
        posXSmall -= BIG_SMALL_H_DISTANCE;
        posXSmall += 0.2; //Little Adjustment
        bigSquare = new Square(posXSmall, posYSmall, Position.DOWN,Size.BIG);
        squarePositions.put(10,bigSquare);

        //Initializes the squares of the left
        posYSmall -= BIG_SMALL_V_DISTANCE;
        for (int i = 11; i < 20; i++) {
            smallSquare = new Square(posXSmall, posYSmall, Position.LEFT,Size.SMALL);
            if (i < 19)
                posYSmall -= SMALL_SMALL_V_DISTANCE;
            squarePositions.put(i,smallSquare);
        }

        //Initializes Parking Square
        posYSmall -= BIG_SMALL_V_DISTANCE;
        bigSquare = new Square(posXSmall, posYSmall, Position.LEFT,Size.BIG);
        squarePositions.put(20,bigSquare);

        //Initializes the squares of the top
        posXSmall += BIG_SMALL_H_DISTANCE;
        for (int i = 21; i < 30; i++) {
            smallSquare = new Square(posXSmall, posYSmall, Position.UP,Size.SMALL);
            if (i < 29)
                posXSmall += SMALL_SMALL_H_DISTANCE;
            squarePositions.put(i,smallSquare);
        }

        //Initializes Go Jail Square
        posXSmall += BIG_SMALL_H_DISTANCE;
        bigSquare = new Square(posXSmall, posYSmall, Position.UP,Size.BIG);
        squarePositions.put(30,bigSquare);

        //Initializes the squares of the right
        posYSmall += BIG_SMALL_V_DISTANCE;
        for (int i = 31; i < 40; i++) {
            smallSquare = new Square(posXSmall, posYSmall, Position.LEFT,Size.SMALL);
            if (i < 39)
                posYSmall += SMALL_SMALL_V_DISTANCE;
            squarePositions.put(i,smallSquare);
        }
    }

    /**
     * Utility function for all functions related to moving the player views.
     * @param player
     *            the player to calculate the position for
     * @return X position relative to the center of the current space, in pixels
     */
    private int calculateSpaceRelativePositionX(Player player, Square sq) {
        int distanceToCenter;
        if (sq.getSize() == Size.BIG)
            distanceToCenter = 12;
        else if (sq.getPos() == Position.DOWN || sq.getPos() == Position.UP)
            distanceToCenter = 8;
        else
            distanceToCenter = 14;
        return Utilities.dpToPx((player.getPlayerID() % 2 == 0 ? 1 : -1) * distanceToCenter,
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
    private int calculateSpaceRelativePositionY(Player player, Square sq) {
        int distanceToCenter;
        if (sq.getSize() == Size.BIG || sq.getPos() == Position.DOWN || sq.getPos() == Position.UP)
            distanceToCenter = 11;
        else
            distanceToCenter = 6;
        return Utilities.dpToPx((player.getPlayerID() / 2 == 0 ? 1 : -1) * distanceToCenter, this);
    }

    /**
     * Loads the current game from memory
     */
    private void loadGame() {
        try {
            this.game = SaveGameHandler.getInstance().loadGame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves the current game in memory
     */
    private void saveGame() {
        if (game != null)
            try {
                SaveGameHandler.getInstance().saveGame(game);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

 	private void drawMoney() {
		ArrayList<Player> playerList = game.getPlayers();
		TextView playerText = (TextView) findViewById(R.id.playerView);
		TextView moneyText = (TextView) findViewById(R.id.moneyView);
		String playersString = new String();
		String moneyString = new String();

		for(Player p : playerList) {
			playersString = playersString.concat(p.getName());
			playersString = playersString.concat("\n");
			moneyString = moneyString.concat((String.valueOf(p.getBalance())));
			moneyString = moneyString.concat("\n");
		}

		moneyText.setText(moneyString);
		playerText.setText(playersString);
	}

    private enum Position{UP,RIGHT,DOWN,LEFT}
    private enum Size{BIG,SMALL}

	/**
	* Inner class that defines the coordinates of the square, its position and its size.
	* It's private as it must be used only in this class
	*/
    private class Square {

        //Attributes
        private double x;
        private double y;
        private Position pos;
        private Size size;

        //Constructor
        public Square(double x, double y, Position pos, Size size) {
            this.x = x;
            this.y = y;
            this.pos = pos;
            this.size = size;
        }

        //Methods
        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public Position getPos() {
            return pos;
        }

        public Size getSize() {
            return size;
        }
    }

}
