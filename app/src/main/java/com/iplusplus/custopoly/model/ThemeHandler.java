package com.iplusplus.custopoly.model;

import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

import java.util.ArrayList;

/**
 * Class that has to:
 *      Store information about what theme is being used.
 *      Return information about what theme is being used.
 */
public class ThemeHandler {

    private static ThemeHandler INSTANCE = null;
    private ArrayList<GameTheme> themes;
    private ShopKeeper shop;
    private int currentTheme;

    /**
     * Constructor of the class. Private as it's a Singleton class
     */
    private ThemeHandler() {
        shop = new ShopKeeper(Custopoly.getAppContext().getString(R.string.themesAndSkinsXML_filepath));
        this.currentTheme = 0;
        //Cheto... todos los precios son 0
        this.themes = new ArrayList<GameTheme>(shop.getThemesInShopList());
    }

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of ThemeHandler that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    public static ThemeHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThemeHandler(); // Create the instance
        }
        return INSTANCE;
    }

    public ShopKeeper getShopKeeperInstance()
    {
        return shop;
    }

    public boolean changePlayerSkin(Player p, PlayerSkin s) {
        boolean changed = false;

        if (this.shop.isPlayerSkinPurchased(s)) {
            changed = true;
            p.setSkin(s);
        }

        return changed;
    }

    /**
     * Method to change the current theme.
     *
     */
    public  void setCurrentTheme(int index) {
        this.currentTheme = index;
    }

    /**
     * Method to change the skin on a particular player.
     *
     * @param skin
     *              Skin to change to, from the PlayerSkins enum.
     * @param playerName
     *              Name of the player whose skin we want changed.
     */
    public void switchPlayerSkinTo (PlayerSkin skin, String playerName) {

    }

    /**
     * Method to retrieve a specific player's skin's resource uri.
     *
     * @param playerName
     *              Name of the player whose skin uri we want.
     * @return
     *              Resource uri to access the image.
     */
    public String getPlayerSkinUri(String playerName) {
        return null;
    }

    /**
     * Method to retrieve the link to the current theme's board graphic.
     *
     * @return
     *          The link to the current theme's board graphic asset.
     */
    public String getBoardGraphicUri() {
        return null;
    }

    /**
     * Returns the theme currently being used.
     *
     * @return
     *          The theme currently on use.
     */
    public GameTheme getCurrentTheme() {
        return this.themes.get(currentTheme);
    }


    /*  /**
     *Add a GameTheme to the arrayList
     *
     * @param GameTheme
     *              a new GameTheme
     */

    public void addGameTheme(GameTheme theme) {
        this.themes.add(theme);
    }

    /**
     * Returns the player skin for the desired player.
     *
     * @param playerName
     *              The name of the player we want the skin from.
     * @return
     *              The skin of the player.
     */
    public static PlayerSkin getPlayerSkin (String playerName) {
        return null;
    }


    public ArrayList<GameTheme> getThemes() {
        return themes;
    }

}