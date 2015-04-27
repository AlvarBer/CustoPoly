package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;

public interface GameFacade {

    // //
    // INPUT METHODS
    // //

    /**
     * Method to move the current player. It will:
     * - Throw the dice.
     * - Show the result to player IN A DIALOG.
     * - Change the position on the current player's class.
     * - Apply any needed bonuses ($200 for crossing the starting line, etc).
     * - Notify that the move has been made.
     */
    public abstract void moveCurrentPlayer();

    /**
     * Changes the turn to the next player.
     */
    public abstract void passTurn();

    // //
    // OBSERVERS
    // //

    /**
     * @return
     *          Returns a list of the NAMES of the assets
     *          owned by the current player.
     */
    public abstract ArrayList<String> getAssetsOwnedByCurrentPlayer();

    /**
     * Observer method
     *  String -> String
     *
     * @param propertyName
     *          Name of the property who's owner's name is needed.
     * @return
     *          The name of the player who owns the property.
     */
    public abstract String getOwnerName(String propertyName);

    /**
     * Observer method
     *  String -> int
     *
     * @param propertyName
     *          Name of the property who's owner's ID is needed.
     * @return
     *          The ID of the player who owns the property.
     */
    public abstract int getOwnerId(String propertyName);


    public abstract Bank getBank();

    /**
     * Observer method
     * -> int
     *
     * @return The number of cells in the board
     */
    public abstract int getBoardSize();

    /**
     * Observer method
     * -> ArrayList<String>
     *
     * @return The names of all the players.
     */
    public abstract ArrayList<String> getPlayerNames();

    /**
     * Observer method
     * -> boolean
     *
     * @return Returns whether the game has ended.
     */
    public abstract boolean isEnded();

    /**
     * Observer method
     * -> String
     *
     * @return Returns the name of the winner
     */
    public abstract String getWinnerName();

    /**
     * Observer method
     * -> String
     *
     * @return Returns the name of the current player
     */
    public abstract String getCurrentPlayerName();

    /**
     * Observer method
     * -> String
     *
     * @return Returns the current theme's background resource path.
     */
    public abstract String getCurrentThemeBackgroundResPath();

    /**
     * Observer method
     * -> String
     *
     * @return Returns the current theme's card resource path.
     */
    public abstract String getCurrentThemeCardsResPath();

/* OLD DEPRECATED METHODS

    public abstract ArrayList<PropertyLand> getAssetsOwnedByPlayer();

    public abstract Player getOwner(PropertyLand property);

    public abstract Bank getBank();

    public abstract Board getBoard();

    public abstract ArrayList<Player> getPlayers();

    public abstract boolean hasWinner();

    public abstract Player getCurrentPlayer();

    public abstract void setCurrentPlayer(Player player);

    public abstract  GameTheme getTheme();
    */

}
