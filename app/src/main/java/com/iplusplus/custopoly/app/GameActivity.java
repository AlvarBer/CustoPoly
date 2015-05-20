package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.SaveGameHandler;
import com.iplusplus.custopoly.model.Utilities;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Corresponds with the game_activity in the mockup.
 * Allows to play a gameFacade, displaying it on the screen. In addition, it allows the communication
   with the gameFacade through a system of buttons
 */

public class GameActivity extends ActionBarActivity implements GameObserver, DiceFragment.DiceDialogListener {

    //Attributes
    private GameFacade gameFacade;
    private ImageView boardBackgroundImageView;
    private Button buyButton;

    //Constants
    private final String BOARDRESOURCE = "activity_game_board_";

    //Test for drawing players
    private TableLayout boardLayout;
    private ArrayList<SquareCell> cells;
    private int boardWidth, boardHeight;
    private ImageView squareImageView;
    private Button viewPropertiesButton;
    private Button endTurn;
    private static final int propertiesViewRequestCode = 1;


    // //
    // Methods implemented from Android events
    // //

    /**
	* Called when GameActivity is created. It's in charge of creating the activity, loading
	* the gameFacade and initializing all the visual components of the view and display them on
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
        gameFacade.addObserver(this);
    }

    /**
     * Called when the GameActivity is hidden. Automatically saves the gameFacade.
     *
     * @see android.app.Activity#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        saveGame();
    }

    /**
     * Called every time the app is resumed. Loads the gameFacade and draw the boardLayout
     * and the players
     *
     * @see android.app.Activity#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Defines the behaviour of the back button.
     * It shows a dialog asking the user to confirm if he wants to quit the gameFacade
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
                        GameActivity.this.finish();
                    }

                })
                .setNegativeButton(getString(R.string.ingame_buynobutton), null)
                .show();
    }
    
    // //
    // Methods implemented from Game events
    // //

    /**
     * Displays the skins of the players according to their current position 
	 * in the boardLayout. If there are more than one player in the same square, it
	 * spreads the players to the border of the square to fit them in them.
	 * Each player is a view that is include in a FrameLayout (players).
     * @param board
     */
    private void drawPlayers(Board board) {

        int boardHeight = this.boardHeight;
        int boardWidth = this.boardWidth;

        for (int i = 0; i < board.getSize(); i++) {
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
     * Displays the boardLayout of the theme on the image of the boardLayout
     * @param theme
     */
    private void drawBoard(GameTheme theme) {
        this.boardBackgroundImageView.setImageResource(Utilities.getResId(theme.getBackgroundPathResource(), R.drawable.class));
    }

    /**
     * Initializes all the components of the view
     */
    private void setupViews() {
        this.boardBackgroundImageView = (ImageView) findViewById(R.id.activity_game_iv_boardBackground);
        //Get the dimensions of the boardLayout
        this.boardBackgroundImageView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.boardWidth = this.boardBackgroundImageView.getMeasuredWidth();
        this.boardHeight = this.boardBackgroundImageView.getMeasuredHeight();

        this.boardLayout = (TableLayout)findViewById(R.id.activity_game_tl_board);
        this.buyButton = (Button) findViewById(R.id.activity_game_bt_buy);
        this.squareImageView = (ImageView) findViewById(R.id.activity_game_iv_square);
        this.viewPropertiesButton  = (Button) findViewById( R.id.activity_game_bt_properties);
        this.endTurn = (Button) findViewById (R.id.activity_game_bt_endTurn);

        //Set action listeners

        viewPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameFacade.viewProperties();
            }
        });

        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameFacade.passTurn();
            }
        });
    }

	/**
     * Initializes the coordinates, size and position of each square of the map
     */
    private void initSquares(GameTheme currentTheme, Board board, ArrayList<Player> playersList) {

        this.cells = new ArrayList<SquareCell>();

        int bigSquareHeight = (this.boardHeight / 13) * 2; //The height of a big square
        int bigSquareWidth = (this.boardWidth / 13) * 2; //The width of a big square
        int posY = this.boardHeight - bigSquareHeight;
        int posX = this.boardWidth - bigSquareWidth;
        Position edge = Position.DOWN;

        for (int i = 0; i < board.getSize(); i++) {

            //Get the resource of the square
            String resourceName = currentTheme.getName() + "_square_" + i;
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
        for (Player p : playersList) {
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
     * Loads the current gameFacade from memory
     */
    private void loadGame() {
        try {
            this.gameFacade = SaveGameHandler.getInstance().loadGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves the current gameFacade in memory
     */
    private void saveGame() {
            try {
                SaveGameHandler.getInstance().saveGame(gameFacade);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

	/**
	 * Draws the money and name of the current player
     * @param currentPlayer
     */
 	private void drawResources(Player currentPlayer) {
		TextView playerText = (TextView) findViewById(R.id.activity_game_tv_player);
		TextView moneyText = (TextView) findViewById(R.id.activity_game_tv_money);

		//We set the text
		playerText.setText(/*"PLAYER" + "\n" + */currentPlayer.getName());
		moneyText.setText(/*"MONEY" + "\n" + */String.valueOf(currentPlayer.getBalance()));

        //Draw square
        int index = currentPlayer.getLandIndex();
        int id = cells.get(index).getResource();
        squareImageView.setImageResource(id);
	}

    // //
    //GameObserver Methods
    // //
    @Override
    public void onAttached(GameTheme theme, Board board, Player currentPlayer, ArrayList<Player> playersList) {
        initSquares(theme, board, playersList);

        onGameBegin(theme, board, currentPlayer);
    }

    @Override
    public void onGameBegin(GameTheme theme, Board board, Player currentPlayer) {
        drawBoard(theme);
        //drawPlayers(boardLayout);
        drawResources(currentPlayer);
        endTurn.setEnabled(false);
        this.buyButton.setEnabled(true);
        onTurnBegin(board, currentPlayer);
    }

    @Override
    public void onGameEnd(Board board, ArrayList<Player> playersList) {

    }

    @Override
    public void onGameReset(GameTheme theme, Board board, Player currentPlayer) {

    }

    @Override
    public void onTurnBegin(final Board board, final Player player) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String turn = player.getName();
        builder.setTitle(turn + " turn")
                .setCancelable(false)
                .setPositiveButton("Roll Dice", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        onRollDiceBegin(board,player);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        this.buyButton.setEnabled(false);

    }

    @Override
    public void onTurnEnd(Board board, Player player) {
        endTurn.setEnabled(false);

    }

    @Override
    public void onViewProperties(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<PropertyLand> mortgagedProperties) {

        //Change activity
        Intent propertiesView = new Intent(this, PropertiesViewActivity.class);

        //Set the arguments to pass to the view
        propertiesView.putExtra("currentPlayer", currentPlayer);
        propertiesView.putExtra("propertiesList", properties);
        propertiesView.putExtra("mortgageList", mortgagedProperties);

        //Create the imageIds array for the properties
        ArrayList<Integer> imageIdsUnMortgaged = new ArrayList<>();
        for(PropertyLand prop: properties)
        {
            imageIdsUnMortgaged.add(cells.get(prop.getLandIndex()).getResource());
        }
        propertiesView.putExtra("imageIdsListUnMortgaged", imageIdsUnMortgaged);

        //Create the imageIds array for the mortgaged properties
        ArrayList<Integer> imageIdsMortgaged = new ArrayList<>();
        for(PropertyLand prop: mortgagedProperties)
        {
            imageIdsMortgaged.add(cells.get(prop.getLandIndex()).getResource());
        }
        propertiesView.putExtra("imageIdsListMortgaged", imageIdsMortgaged);

        //Start activity with result code
         startActivityForResult(propertiesView, propertiesViewRequestCode);
    }

    @Override
    public void onMortgage(Player currentPlayer) {
        drawResources(currentPlayer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Handle requests
        if (requestCode == propertiesViewRequestCode) {
            if(resultCode == RESULT_OK){
                String mortageLandName = data.getStringExtra("mortgageLand");

                //Call the facade for morgage
                gameFacade.mortgageProperty(mortageLandName);
            }
            if (resultCode == RESULT_CANCELED) {
               //Do nothing
            }
        }
    }

    @Override
    public void onRollDiceBegin(Board board, Player currentPlayer) {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = DiceFragment.newInstance("dialog");
        newFragment.show(ft, "dialog");
    }

    @Override
    public void onRollDiceEnd(Board board, Player currentPlayer) {
        drawPlayers(board);
        drawResources(currentPlayer);
        endTurn.setEnabled(true);

    }

    @Override
    public void onCard(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //TODO:Display in the title what kind of card is. Necessary to pass another argument with this information (Use .setTitle())

        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onPayFee(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onPurchasableCard(final String playerName, final PropertyLand land) {
        this.buyButton.setEnabled(true);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = Custopoly.getAppContext();

                String message = context.getText(R.string.ingame_askWantToBuy).toString();
                String formatMessage = String.format(message, land.getName(), land.getPrice());
                String title = playerName;

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle(title).setMessage(formatMessage).setPositiveButton(context.getString(R.string.ingame_buyyesbutton), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        gameFacade.buyAsset();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(context.getString(R.string.ingame_buynobutton), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String s = context.getResources().getText(R.string.ingame_notWantToBuy).toString();
                        String fs = String.format(s, playerName, land.getName());
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameActivity.this);
                        builder2.setTitle(fs);
                        builder2.setMessage(fs);
                        dialog.dismiss();
                    }
                });
                builder.show();
                }

            });
    }

    @Override
    public void onBoughtCard(Player currentPlayer, PropertyLand land) {
        Context context = Custopoly.getAppContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String s = context.getResources().getText(R.string.ingame_buySuccess).toString();
        String message = String.format(s,
                currentPlayer.getName(), land.getName());
        drawResources(currentPlayer);
        buyButton.setEnabled(false);
    }

    @Override
    public void onBuyError(Player currentPlayer, PropertyLand land) {
        Context context = Custopoly.getAppContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        String s = context.getResources().getText(R.string.ingame_buyFailure).toString();
        String message = String.format(s, currentPlayer.getName());
        builder.setTitle("Not possible to buy").setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onFinishDiceDialog(int diceResult) {
        gameFacade.rollDice(diceResult);
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

                playerView.setX(posX + boardBackgroundImageView.getX());
                playerView.setY(posY);

                playerView.setImageResource(Utilities.getResId(this.playerSkins.get(0),R.drawable.class));
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
