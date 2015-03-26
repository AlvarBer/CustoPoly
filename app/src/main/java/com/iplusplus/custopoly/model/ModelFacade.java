package com.iplusplus.custopoly.model;


import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.element.Land;

import java.util.Collection;

/**
 * SINGLETON class that has to:
 *              Provide an access point for the view.
 *              Initially, it will just have a copy of all the
 *              public methods of ShopKeeper, ThemeHandler and Game.
 *              EXAMPLE:
 *                       public static boolean isThemePurchased(Themes theme) {
 *                          return ShopKeeper.getInstance().isThemePurchased(theme);
 *                       }
 */
public class ModelFacade {

    private static ModelFacade INSTANCE;

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of ModelFacade that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    public static ModelFacade getInstance() {
        return INSTANCE;
    }

    //-------------
    // GAME methods
    //-------------

    /**
     * It instantiates all things necessary to begin a game.
     *
     * @param names
     *          A list containing the names of the players.
     */
    public static void createGame(Collection<String> names) {}

    /**
     * Method that allows a player to purchase a property.
     *
     * @param land
     *          Cell in which the asset is located.
     */
    public static void buyAsset(Land land){}

    /**
     * Moves the player.
     *
     * @param playerName
     *          The name of the player that has to move.
     */
    public static void move(String playerName) {}

    /**
     * @param playerName
     *          The name of the player of which we want the assets.
     * @return
     *          Returns the asset list with the assets owned by.
     */
    public static PropertyLand getAssetsOwnedByPlayer(String playerName) {
        return null;
    }



    //-------------------
    // SHOPKEEPER methods
    //-------------------

    /**
     * Add some points to the player's wallet.
     *
     * @param points
     *              Number of points to add.
     */
    public static void addPoints(int points) {}

    /**
     * Method to know how many points the owner has.
     *
     * @return
     *          Number of points in the wallet.
     */
    public static int getPoints() {return 0;}

    //THEME HANDLING

    /**
     * Method to know if a given theme has already been purchased.
     *
     * @param theme
     *              The theme in question
     * @return
     *              True if the theme has been purchased,
     *              False otherwise.
     */
    public static boolean isThemePurchased(Themes theme) { return false;}

    /**
     * Method to buy a theme.
     * It should also mark the corresponding player skins as purchased.
     *
     * @param theme
     *              GameTheme to be bought.
     */
    public static void buyTheme(Themes theme) {}

    /**
     * Method to know how much a theme costs
     *
     * @param theme
     *              The theme in question.
     * @return
     *              The number of points it costs.
     */
    public static int getThemeCost(Themes theme) {return 0;}

    //PLAYER SKIN HANDLING

    /**
     * Method to know if a certain player skin has been purchased.
     *
     * @param skin
     *              Skin to consult.
     * @return
     *              True if the skin has been purchased,
     *              False otherwise.
     */
    public static boolean isPlayerSkinPurchased (PlayerSkins skin) { return false;}

    /**
     * Method to buy a certain player skin.
     *
     * @param skin
     *              Skin to be bought.
     */
    public static void buyPlayerSkin(PlayerSkins skin) {}

    /**
     * Method to know how much a certain skin costs.
     *
     * @param skin
     *              The skin in question.
     * @return
     *              The price of the skin.
     */
    public static int getPlayerSkinCost (PlayerSkins skin) {return 0;}

    //---------------------
    // THEMEHANDLER methods
    //---------------------

    /**
     * Method to change the current theme.
     *
     * @param theme
     *              The theme it should change to.
     */
    public static void switchThemeTo(Themes theme) {}

    /**
     * Method to change the skin on a particular player.
     *
     * @param skin
     *              Skin to change to, from the PlayerSkins enum.
     * @param playerName
     *              Name of the player whose skin we want changed.
     */
    public static void switchPlayerSkinTo (PlayerSkins skin, String playerName) { }

    /**
     * Method to retrieve a specific player's skin's resource uri.
     *
     * @param playerName
     *              Name of the player whose skin uri we want.
     * @return
     *              Resource uri to access the image.
     */
    public static String getPlayerSkinUri(String playerName) {
        return null;
    }

    /**
     * Method to retrieve the link to the current theme's board graphic.
     *
     * @return
     *          The link to the current theme's board graphic asset.
     */
    public static String getBoardGraphicUri() {
        return null;
    }

    /**
     * Returns the theme currently being used.
     *
     * @return
     *          The theme currently on use.
     */
    public static Themes getCurrentTheme() {
        return null;
    }

    /**
     * Returns the player skin for the desired player.
     *
     * @param playerName
     *              The name of the player we want the skin from.
     * @return
     *              The skin of the player.
     */
    public static PlayerSkins getPlayerSkin (String playerName) {return null;}
}
