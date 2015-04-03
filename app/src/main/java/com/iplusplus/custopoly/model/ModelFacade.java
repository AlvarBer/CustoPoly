/*
package com.iplusplus.custopoly.model;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import java.util.ArrayList;
import java.util.Collection;
*/
/**
 * SINGLETON class that has to:
 *              Provide an access point for the view.
 *              Initially, it will just have a copy of all the
 *              public methods of ShopKeeper, ThemeHandler and Game.
 *              EXAMPLE:
 *                       public static boolean isThemePurchased(Themes theme) {
 *                          return ShopKeeper.getInstance().isThemePurchased(theme);
 *                       }
 *//*



public class ModelFacade {


    private static GameFacade game;
    private static ShopKeeper shopKeeper;
    private static ThemeHandler themeHandler;

    //-------------
    // GAME methods
    //-------------
    */
/**
     * It instantiates all things necessary to begin a game.
     *
     * @param names
     *          A list containing the names of the players.
     *//*

    public static void createGame(Collection<String> names) {
        GameFacade.createGame(names);
    }
    */
/**
     * Method that allows a player to purchase a property.
     *
     * @param land
     *          Cell in which the asset is located.
     *//*

    public static void buyAsset(Land land){
        GameFacade.buyAsset(land);
    }
    */
/**
     * Moves the player.
     *
     * @param playerName
     *          The name of the player that has to move.
     *//*

    public static void move(String playerName) {
        GameFacade.move(playerName);
    }
    */
/**
     * @param playerName
     *          The name of the player of which we want the assets.
     * @return
     *          Returns the asset list with the assets owned by.
     *//*

    public static ArrayList<PropertyLand> getAssetsOwnedByPlayer(String playerName) {
        return GameFacade.getAssetsOwnedByPlayer(playerName);
    }
    //-------------------
    // SHOPKEEPER methods
    //-------------------
    */
/**
     * Add some points to the player's wallet.
     *
     * @param points
     *              Number of points to add.
     *//*

    public static void addPoints(int points) {
        shopKeeper.addPoints(points);
    }
    */
/**
     * Method to know how many points the owner has.
     *
     * @return
     *          Number of points in the wallet.
     *//*

    public static int getPoints() {return shopKeeper.getPoints();}
    //THEME HANDLING
    */
/**
     * Method to know if a given theme has already been purchased.
     *
     * @param theme
     *              The theme in question
     * @return
     *              True if the theme has been purchased,
     *              False otherwise.
     *//*

    public static boolean isThemePurchased(GameTheme theme) { return shopKeeper.isThemePurchased(theme);}
    */
/**
     * Method to buy a theme.
     * It should also mark the corresponding player skins as purchased.
     *
     * @param theme
     *              GameTheme to be bought.
     *//*

    public static void buyTheme(GameTheme theme) {shopKeeper.buyTheme(theme);}
    */
/**
     * Method to know how much a theme costs
     *
     * @param theme
     *              The theme in question.
     * @return
     *              The number of points it costs.
     *//*

    public static double getThemeCost(GameTheme theme) {return shopKeeper.getThemeCost(theme.getName());}
    //PLAYER SKIN HANDLING
    */
/**
     * Method to know if a certain player skin has been purchased.
     *
     * @param skin
     *              Skin to consult.
     * @return
     *              True if the skin has been purchased,
     *              False otherwise.
     *//*

    public static boolean isPlayerSkinPurchased (PlayerSkin skin) { return shopKeeper.isPlayerSkinPurchased(skin);}
    */
/**
     * Method to buy a certain player skin.
     *
     * @param skin
     *              Skin to be bought.
     *//*

    public static void buyPlayerSkin(PlayerSkin skin) {shopKeeper.buyPlayerSkin(skin);}
    */
/**
     * Method to know how much a certain skin costs.
     *
     * @param skin
     *              The skin in question.
     * @return
     *              The price of the skin.
     *//*

    public static double getPlayerSkinCost (PlayerSkin skin) {return shopKeeper.getPlayerSkinCost(skin.getName());}
    //---------------------
    // THEMEHANDLER methods
    //---------------------
    */
/**
     * Method to change the current theme.
     *
     * @param theme
     *              The theme it should change to.
     *//*

    public static void switchThemeTo(GameTheme theme) {themeHandler.switchThemeTo(theme);}
    */
/**
     * Method to change the skin on a particular player.
     *
     * @param skin
     *              Skin to change to, from the PlayerSkins enum.
     * @param playerName
     *              Name of the player whose skin we want changed.
     *//*

    public static void switchPlayerSkinTo (PlayerSkin skin, String playerName) {themeHandler.switchPlayerSkinTo(skin,playerName); }
    */
/**
     * Method to retrieve a specific player's skin's resource uri.
     *
     * @param playerName
     *              Name of the player whose skin uri we want.
     * @return
     *              Resource uri to access the image.
     *//*

    public static String getPlayerSkinUri(String playerName) {
        return themeHandler.getPlayerSkinUri(playerName);
    }
    */
/**
     * Method to retrieve the link to the current theme's board graphic.
     *
     * @return
     *          The link to the current theme's board graphic asset.
     *//*

    public static String getBoardGraphicUri() {
        return themeHandler.getBoardGraphicUri();
    }
    */
/**
     * Returns the theme currently being used.
     *
     * @return
     *          The theme currently on use.
     *//*

    public static GameTheme getCurrentTheme() {
        return themeHandler.getCurrentTheme();
    }
    */
/**
     * Returns the player skin for the desired player.
     *
     * @param playerName
     *              The name of the player we want the skin from.
     * @return
     *              The skin of the player.
     *//*

    public static PlayerSkin getPlayerSkin (String playerName) {return themeHandler.getPlayerSkin(playerName);}
}
*/
