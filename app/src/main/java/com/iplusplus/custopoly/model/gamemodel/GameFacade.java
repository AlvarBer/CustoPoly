package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.gamemodel.Observer.GameObservable;

public interface GameFacade extends GameObservable {

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
    //public abstract void moveCurrentPlayer();

    /**
     * Changes the turn to the next player.
     */
    public abstract void passTurn();

    public void buyAsset();

    public void viewProperties();

    public void rollDice();


    // //
    // OBSERVERS
    // //

    //Methods with assets and players
    /**
     * GameObserver method
     *  String -> String
     *
     * @param propertyName
     *          Name of the property who's owner's name is needed.
     * @return
     *          The name of the player who owns the property.
     */
   // public abstract String getOwnerName(String propertyName);

    /**
     * GameObserver method
     *  String -> int
     *
     * @param propertyName
     *          Name of the property who's owner's ID is needed.
     * @return
     *          The ID of the player who owns the property.
     */
    //public abstract int getOwnerId(String propertyName);

    /**
     * GameObserver method
     * -> ArrayList<String>
     *
     * @return Returns a list of the NAMES of the assets
     * owned by the current player.
     */
    //public abstract ArrayList<String> getAssetsOwnedByCurrentPlayer();

    /**
     * GameObserver method
     *  int -> ArrayList<String>
     *
     * @param playerId
     *          Id of the player that we want to consult
     * @return
     *          A list with all the properties owned by the player
     */
   // public abstract ArrayList<String> getAssetNamesOwnedByPlayer(int playerId);

    /**
     * GameObserver method
     *  String -> ArrayList<String>
     *
     * @param playerName
     *          Name of the player that we want to consult
     * @return
     *          A list with all the properties owned by the player
     */
   // public abstract ArrayList<String> getAssetNamesOwnedByPlayer(String playerName);

    //public abstract Bank getBank();

    // //
    //Observers for players
    // //

    /**
     *
     * @return Player that is able to play in the current turn
     */
   // Player getCurrentPlayer();

    /**
     *
     * @param player Sets the new current player
     */
    //void setCurrentPlayer(Player player);

    /**
     *
     * @return List of players playing
     */
    //ArrayList<Player> getPlayers();

    /**
     * GameObserver method
     * -> ArrayList<String>
     *
     * @return The names of all the players.
     */
   // public abstract ArrayList<String> getPlayerNames();

    /**
     * GameObserver method
     * String -> int
     *
     * @param playerName
     *          The name of the player who's id we want
     * @return
     *          The id of the player.
     */
   // public abstract int getPlayerIdByName(String playerName);

    /**
     * GameObserver method
     * int -> String
     *
     * @param playerID The id of the player who's name we want
     * @return The name of the player.
     */
    //public abstract String getPlayerNameById(int playerID);

    /**
     * GameObserver method
     * -> String
     *
     * @return Returns the name of the current player
     */
   // public abstract String getCurrentPlayerName();

    /**
     * GameObserver method
     * -> int
     *
     * @return Returns the name of the current player
     */
    //public abstract int getCurrentPlayerId();

    // //
    //Balance observers
    // //

    /**
     * GameObserver method
     * int -> int
     *
     * @param playerId The id of the player who's balance we want.
     * @return The amount of in-game currency owned by the player.
     */
    //public abstract int getPlayerBalanceById(int playerId);

    /**
     * GameObserver method
     * String -> int
     *
     * @param playerName The name of the player who's balance we want.
     * @return The amount of in-game currency owned by the player.
     */
   // public abstract int getPlayerBalanceByName(String playerName);

    /**
     * GameObserver method
     * -> int
     *
     * @return The amount of in-game currency owned by the current player.
     */
   // public abstract int getCurrentPlayerBalance();

    // //
    // Card observers
    // //

    /**
     * GameObserver method
     * -> ArrayList<String>
     *
     * @return A list of all the cards owned by the player.
     */
    //public abstract ArrayList<String> getCurrentPlayerCardList();

    /**
     * GameObserver method
     * int -> ArrayList<String>
     *
     * @param playerId Id of the player that we want to consult
     * @return A list with all the properties cards owned by the player
     */
  //  public abstract ArrayList<String> getPlayerCardListById(int playerId);

    /**
     * GameObserver method
     * String -> ArrayList<String>
     *
     * @param playerName The name of the player who's cards we want.
     * @return The names of all the cards owned by the player.
     */
    //public abstract ArrayList<String> getPlayerCardListByName(String playerName);

    // //
    //Game logic observers
    // //

    /**
     * GameObserver method
     * -> boolean
     *
     * @return Returns whether the game has ended.
     */
   // public abstract boolean isEnded();

    /**
     * GameObserver method
     * -> String
     *
     * @return Returns the name of the winner
     */
    //public abstract String getWinnerName();

    // //
    //Resource observers
    // //

    /**
     * GameObserver method
     * -> String
     *
     * @return
     *          A String with the path to the resource of the player skin.
     */
   // public abstract String getCurrentPlayerSkinResPath();

    /**
     * GameObserver method
     *  int -> String
     *
     * @param playerId
     *          Id of the player that we want to consult.
     * @return
     *          A String with the path to the resource of the player skin.
     */
    //public abstract String getPlayerSkinResPathById(int playerId);

    /**
     * GameObserver method
     *  String -> String
     *
     * @param playerName
     *          Name of the player that we want to consult.
     * @return
     *          A String with the path to the resource of the player skin.
     */
   // public abstract String getPlayerSkinResPathByName(String playerName);

    /**
     *
     * @return Current theme of the game
     */
    //GameTheme getCurrentTheme();

    /**
     * GameObserver method
     * -> String
     *
     * @return Returns the current theme's background resource path.
     */
   // public abstract String getCurrentThemeBackgroundResPath();

    /**
     * GameObserver method
     * -> String
     *
     * @return Returns the current theme's community box card resource path.
     */
   // public abstract String getCurrentThemeCCBoxCardResPath();

    /**
     * GameObserver method
     * -> String
     *
     * @return Returns the current theme's fortune card resource path.
     */
   // public abstract String getCurrentThemeFortuneCardResPath();

    // //
    // Game attribute observers
    // //

    /**
     * GameObserver method
     * -> int
     *
     * @return The number of cells in the board
     */
   // public abstract int getBoardSize();

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
