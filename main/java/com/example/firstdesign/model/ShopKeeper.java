package com.example.firstdesign.model;

/**
 * SINGLETON class that has to:
 *          Keep track of the {outsideofgame} points of the player.
 *          Keep track of the themes the player has bought.
 *          Keep a list of prices for each item.
 */
public class ShopKeeper {


    private static ShopKeeper INSTANCE;

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of ShopKeeper that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    public static ShopKeeper getInstance() {
        return INSTANCE;
    }

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
    public static boolean isThemePurchased(Themes theme) {return false;}

    /**
     * Method to buy a theme.
     * It should also mark the corresponding player skins as purchased.
     *
     * @param theme
     *              Theme to be bought.
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

}
