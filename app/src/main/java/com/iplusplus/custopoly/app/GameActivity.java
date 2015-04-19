package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;

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
    private Game game;
    private ImageView boardBackground;
    private TableLayout board;
    private HashMap<Integer,Square> squares;
    private Button buyButton;

    //Constants
    private final String BOARDRESOURCE = "activity_game_board_";


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
        initSquares();
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
        float x = 0, y = 0;
        Square sqPos;
        GridLayout layout;



        for (Player player : game.getPlayers()) {
            //Gets the position (Square) of the square in which the player is from the map of positions
            sqPos = squares.get(player.getLandIndex());
            layout = sqPos.getLayout();
            layout.removeAllViews();

        }

		//The following process is repeated for each player in the game
        for (Player player : game.getPlayers()) {
            //Gets the position (Square) of the square in which the player is from the map of positions
            sqPos = squares.get(player.getLandIndex());
            layout = sqPos.getLayout();
            View playerView = inflater.inflate(R.layout.fragment_player, layout,false);
            ImageView skin = (ImageView) playerView.findViewById(R.id.fragment_player_iv_skin);
            skin.setImageResource(getResources().getIdentifier(player.getSkin().getImageResourceName(), "drawable", getPackageName()));

            //FrameLayout.LayoutParams p = new RelativeLayout.LayoutParams(40,40);
            //playerView.setLayoutParams(p);
            //playerView.requestLayout();

            //Set the size of the skin
            skin.setScaleX((float) 0.5);
            skin.setScaleY((float) 0.5);

            playerView.setTag(i);


            //Defines the position of the player in the center of the square
            x = Utilities.dpToPx((int) (layout.getX()), this);
            y = Utilities.dpToPx((int) (layout.getY()), this);

            //If the position is already occupied by another player, spreads the player to the border of the square
            if (isSharedSquare(game.getPlayers(), player.getLandIndex())) {
                x += calculateSpaceRelativePositionX(player, sqPos);
                y += calculateSpaceRelativePositionY(player, sqPos);
            }

            //Set the position of the player and add it to view
            playerView.setX(x);
            playerView.setY(y);
            layout.addView(playerView);
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
        this.board = (TableLayout)findViewById(R.id.activity_game_tl_board);
        this.buyButton = (Button) findViewById(R.id.activity_game_bt_buy);

        //Set action listeners

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Player p : game.getPlayers()) {
                    if (p.getLandIndex() >= 39) p.setLandIndex(0);
                    else p.setLandIndex(p.getLandIndex() + 1);
                }
                drawPlayers();
            }
        });
    }

	/**
	* Initializes the coordinates, size and position of each square of the map
	*/
    private void initSquares() {
        squares = new HashMap<Integer,Square>();
        String resourceName;
        int id;
        Square square;
        Position pos;
        Size size;
        GridLayout squareLayout;

        for (int i = 0; i < 40; i++) {

            resourceName = BOARDRESOURCE + i;
            id = Utilities.getResId(resourceName,R.id.class);
            if (i == 0 || i == 10 || i == 20 || i == 30 )  size = Size.BIG;
            else size = Size.SMALL;

            if (i <= 10) pos = Position.DOWN;
            else if( i <= 20) pos = Position.LEFT;
            else if( i <= 20) pos = Position.UP;
            else  pos = Position.RIGHT;

            squareLayout = (GridLayout) findViewById(id);



            square = new Square (squareLayout,pos,size);

            squares.put(i,square);
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
        //TODO: Remove casting when the facade is properly integrated
        try {
            this.game = SaveGameHandler.getInstance().loadGame();
        } catch (Exception e) {
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
		TextView playerText = (TextView) findViewById(R.id.activity_game_tv_player);
		TextView moneyText = (TextView) findViewById(R.id.activity_game_tv_money);
		String playersString = "";
		String moneyString = "";

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
        private GridLayout layout;
        private Position pos;
        private Size size;

        public Square(GridLayout layout, Position pos, Size size) {
            this.layout = layout;
            this.pos = pos;
            this.size = size;
        }

        public GridLayout getLayout() {
            return layout;
        }

        public Position getPos() {
            return pos;
        }

        public Size getSize() {
            return size;
        }
    }

}
