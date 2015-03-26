package com.iplusplus.custopoly.model;

/**
 * Class that has to:
 *      Store information about what theme is being used.
 *      Return information about what theme is being used.
 */
public class ThemeHandler {

    private static ThemeHandler INSTANCE;

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of ThemeHandler that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    public static ThemeHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Method to change the current theme.
     *
     * @param theme
     *              The theme it should change to.
     */
    public static void switchThemeTo(GameTheme theme) {}

    /**
     * Method to change the skin on a particular player.
     *
     * @param skin
     *              Skin to change to, from the PlayerSkins enum.
     * @param playerName
     *              Name of the player whose skin we want changed.
     */
    public static void switchPlayerSkinTo (PlayerSkin skin, String playerName) { }

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
    public static GameTheme getCurrentTheme() {
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
    public static PlayerSkin getPlayerSkin (String playerName) {return null;}
}
