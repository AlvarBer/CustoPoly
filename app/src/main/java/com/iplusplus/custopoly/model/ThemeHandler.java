package com.iplusplus.custopoly.model;

/**
 * Class that has to:
 *      Store information about what theme is being used.
 *      Return information about what theme is being used.
 */
public class ThemeHandler {

    private static ThemeHandler INSTANCE;
    private GameTheme currentTheme; 
    
    /**
     * Constructor of the class. Private as it's a Singleton class
     */
    private ThemeHandler() {
    	    	
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

    

    /*public boolean changePlayerSkin(Player p, PlayerSkin s) {
    	boolean changed = false;
    	
    	if (isPlayerSkinPurchased(s))
    		changed = true;
    		// p.setSkin(s); TODO Falta por implementar en la clase Player
    	
    	return changed;
    }/*

    
    /**
     * Method to change the current theme.
     *
     * @param theme
     *              The theme it should change to.
     */
    public  void switchThemeTo(GameTheme theme) {
    	this.currentTheme = theme;
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
        return this.currentTheme;
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
    
    
}


