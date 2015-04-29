package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.iplusplus.custopoly.Custopoly;
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
    private Button buyButton;

    //Constants
    private final String BOARDRESOURCE = "activity_game_board_";

    //Test for drawing players
    private ArrayList<SquareCell> cells;
    private int boardWidth, boardHeight;
    private ImageView squareImage;

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
		drawResources();
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
		drawResources();
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

        int boardHeight = this.boardHeight;
        int boardWidth = this.boardWidth;

        for (int i = 0; i < this.game.getBoardSize(); i++) {
            if (this.cells.get(i).playerSkins.size() != 0) {
                ImageView cellView = this.cells.get(i).createImageOfPlayersInCell();
                this.addContentView(cellView, new ActionBar.LayoutParams((this.cells.get(i).posX),
                        this.cells.get(i).posY));
            }
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
        //Get the dimensions of the board
        this.boardBackground.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.boardWidth = this.boardBackground.getMeasuredWidth();
        this.boardHeight = this.boardBackground.getMeasuredHeight();

        this.board = (TableLayout)findViewById(R.id.activity_game_tl_board);
        this.buyButton = (Button) findViewById(R.id.activity_game_bt_buy);
        this.squareImage = (ImageView) findViewById(R.id.activity_game_iv_square);

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

        this.cells = new ArrayList<SquareCell>();

        int bigSquareHeight = (this.boardHeight / 13) * 2; //The height of a big square
        int bigSquareWidth = (this.boardWidth / 13) * 2; //The width of a big square
        int posY = this.boardHeight - bigSquareHeight;
        int posX = this.boardWidth - bigSquareWidth;
        Position edge = Position.DOWN;

        for (int i = 0; i < this.game.getBoardSize(); i++) {

            //Get the resource of the square
            String resourceName = game.getTheme().getName() + "_square_" + i;
            int resource = Utilities.getResId(resourceName,R.drawable.class);

            //Create square
            if (i % 10 == 0) {
                //Create big square
                this.cells.add(new SquareCell(posX, posY, bigSquareWidth, bigSquareHeight,resource));
                //Change current edge
                edge = Position.nextPosition(edge);
            } else {
                //Create small square
                switch (edge) {

                    case UP:
                        this.cells.add(new SquareCell(posX, posY, bigSquareWidth / 2, bigSquareHeight,resource));
                        break;
                    case RIGHT:
                        this.cells.add(new SquareCell(posX, posY, bigSquareWidth, bigSquareHeight / 2,resource));
                        break;
                    case DOWN:
                        this.cells.add(new SquareCell(posX, posY, bigSquareWidth / 2, bigSquareHeight,resource));
                        break;
                    case LEFT:
                        this.cells.add(new SquareCell(posX, posY, bigSquareWidth, bigSquareHeight / 2,resource));
                        break;
                }
            }
            //Update position
            switch (edge) {

                case UP:
                    posX += bigSquareWidth / 2;
                    break;
                case RIGHT:
                    posY += bigSquareHeight / 2;
                    break;
                case DOWN:
                    posX -= bigSquareWidth / 2;
                    break;
                case LEFT:
                    posY -= bigSquareHeight / 2;
                    break;
            }
        }

        //Fill in with players
        for (Player p : this.game.getPlayers()) {
            this.cells.get(p.getLandIndex()).addPlayerSkin(p.getSkin().getImageResourceName());
        }
    }

    /**
     * Utility function for all functions related to moving the player views.
     * @param player
     *            the player to calculate the position for
     * @return X position relative to the center of the current space, in pixels

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
    }*/

    /**
     * Utility function for all functions related to moving the player views.
     * Calculates the Y position of a player, so that the players don't all sit
     * on the same spot.
     *
     * @param player
     *            the player to calculate the position for
     * @return Y position in pixels

    private int calculateSpaceRelativePositionY(Player player, Square sq) {
        int distanceToCenter;
        if (sq.getSize() == Size.BIG || sq.getPos() == Position.DOWN || sq.getPos() == Position.UP)
            distanceToCenter = 11;
        else
            distanceToCenter = 6;
        return Utilities.dpToPx((player.getPlayerID() / 2 == 0 ? 1 : -1) * distanceToCenter, this);
    }*/

    /**
     * Loads the current game from memory
     */
    private void loadGame() {
        try {
            this.game = (Game) SaveGameHandler.getInstance().loadGame();
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

 	private void drawResources() {

        //Draw Money
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

        //Draw square
        int index = game.getCurrentPlayer().getLandIndex();
        int id = cells.get(index).getResource();
        squareImage.setImageResource(id);
	}

    private enum Position {
        UP, RIGHT, DOWN, LEFT;

        public static Position nextPosition(Position edge) {
            Position newEdge = DOWN;
            switch (edge) {

                case UP:
                    newEdge = RIGHT;
                    break;
                case RIGHT:
                    newEdge = DOWN;
                    break;
                case DOWN:
                    newEdge = LEFT;
                    break;
                case LEFT:
                    newEdge = UP;
                    break;
            }
            return newEdge;
        }
    }

	/**
	* Inner class that defines the coordinates of the square, its position and its size.
	* It's private as it must be used only in this class
	*/

    private class SquareCell {

        private int posX, posY; //Upper-left coordinates
        private int width, height;
        private int resource;

        private ArrayList<String> playerSkins;

        public SquareCell(int posX, int posY, int width, int height,int resource) {
            this.posX = posX;
            this.posY = posY;
            this.width = width;
            this.height = height;
            this.resource = resource;
            this.playerSkins = new ArrayList<String>();
        }

        public void addPlayerSkin(String skin) {
            playerSkins.add(skin);
        }


        public ImageView createImageOfPlayersInCell() {
            ImageView playerView = new ImageView(Custopoly.getAppContext());
            if (this.playerSkins.size() == 1) {
                playerView.setMaxWidth(this.width);
                playerView.setMinimumWidth(this.width);
                //playerView.setMaxHeight(this.height);
                playerView.setMinimumHeight(this.height);

                playerView.setX(posX + boardBackground.getX());
                playerView.setY(posY);

                playerView.setImageResource(Custopoly.getAppContext().getResources().getIdentifier(this.playerSkins.get(0), "drawable", getPackageName()));
            } else if (this.playerSkins.size() > 1) {
                //TODO: IMPLEMENT
            }

            return playerView;
        }

        public int getResource() {
            return resource;
        }
    }

}
