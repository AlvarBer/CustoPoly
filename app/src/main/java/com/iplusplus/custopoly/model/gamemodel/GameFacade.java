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

    //Methods with assets and players
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

    /**
     * Observer method
     * -> ArrayList<String>
     *
     * @return Returns a list of the NAMES of the assets
     * owned by the current player.
     */
    public abstract ArrayList<String> getAssetsOwnedByCurrentPlayer();

    /**
     * Observer method
     *  int -> ArrayList<String>
     *
     * @param playerId
     *          Id of the player that we want to consult
     * @return
     *          A list with all the properties owned by the player
     */
    public abstract ArrayList<String> getAssetNamesOwnedByPlayer(int playerId);

    /**
     * Observer method
     *  String -> ArrayList<String>
     *
     * @param playerName
     *          Name of the player that we want to consult
     * @return
     *          A list with all the properties owned by the player
     */
    public abstract ArrayList<String> getAssetNamesOwnedByPlayer(String playerName);

    public abstract Bank getBank();

    // //
    //Observers for players
    // //

    /**
     * Observer method
     * -> ArrayList<String>
     *
     * @return The names of all the players.
     */
    public abstract ArrayList<String> getPlayerNames();

    /**
     * Observer method
     * String -> int
     *
     * @param playerName
     *          The name of the player who's id we want
     * @return
     *          The id of the player.
     */
    public abstract int getPlayerIdByName(String playerName);

    /**
     * Observer method
     * int -> String
     *
     * @param playerID The id of the player who's name we want
     * @return The name of the player.
     */
    public abstract String getPlayerNameById(int playerID);

    /**
     * Observer method
     * -> String
     *
     * @return Returns the name of the current player
     */
    public abstract String getCurrentPlayerName();

    /**
     * Observer method
     * -> int
     *
     * @return Returns the name of the current player
     */
    public abstract int getCurrentPlayerId();

    // //
    //Balance observers
    // //

    /**
     * Observer method
     * int -> int
     *
     * @param playerId The id of the player who's balance we want.
     * @return The amount of in-game currency owned by the player.
     */
    public abstract int getPlayerBalanceById(int playerId);

    /**
     * Observer method
     * String -> int
     *
     * @param playerName The name of the player who's balance we want.
     * @return The amount of in-game currency owned by the player.
     */
    public abstract int getPlayerBalanceByName(String playerName);

    /**
     * Observer method
     * -> int
     *
     * @return The amount of in-game currency owned by the current player.
     */
    public abstract int getCurrentPlayerBalance();

    // //
    // Card observers
    // //

    /**
     * Observer method
     * -> ArrayList<String>
     *
     * @return A list of all the cards owned by the player.
     */
    public abstract ArrayList<String> getCurrentPlayerCardList();

    /**
     * Observer method
     * int -> ArrayList<String>
     *
     * @param playerId Id of the player that we want to consult
     * @return A list with all the properties cards owned by the player
     */
    public abstract ArrayList<String> getPlayerCardListById(int playerId);

    /**
     * Observer method
     * String -> ArrayList<String>
     *
     * @param playerName The name of the player who's cards we want.
     * @return The names of all the cards owned by the player.
     */
    public abstract ArrayList<String> getPlayerCardListByName(String playerName);

    // //
    //Game logic observers
    // //

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

    // //
    //Resource observers
    // //
    /**
     * Observer method
     * -> String
     *
     * @return
     *          A String with the path to the resource of the player skin.
     */
    public abstract String getCurrentPlayerSkinResPath();

    /**
     * Observer method
     *  int -> String
     *
     * @param playerId
     *          Id of the player that we want to consult.
     * @return
     *          A String with the path to the resource of the player skin.
     */
    public abstract String getPlayerSkinResPathById(int playerId);

    /**
     * Observer method
     *  String -> String
     *
     * @param playerName
     *          Name of the player that we want to consult.
     * @return
     *          A String with the path to the resource of the player skin.
     */
    public abstract String getPlayerSkinResPathByName(String playerName);

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

    // //
    // Game attribute observers
    // //

    /**
     * Observer method
     * -> int
     *
     * @return The number of cells in the board
     */
    public abstract int getBoardSize();

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
