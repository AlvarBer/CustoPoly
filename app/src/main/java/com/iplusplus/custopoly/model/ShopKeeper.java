package com.iplusplus.custopoly.model;

import com.iplusplus.custopoly.model.gamemodel.cellelements.Infrastructure;

import java.util.ArrayList;

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
    public static ShopKeeper getInstance()
    {
        if(INSTANCE == null)
        {
            return new ShopKeeper();
        }
        return INSTANCE;
    }


    //Variable that keeps track of the player points
    private int _playerPoints;
    //List containing the player purchased themes
    private ArrayList<Themes> _purchasedThemesList;
    //List containing the player purchased skins
    private ArrayList<PlayerSkins> _purchasedPlayerSkinsList;


    /**
     * Add some points to the player's wallet.
     *
     * @param points
     *              Number of points to add.
     */
    public void addPoints(int points)
    {
        _playerPoints += points;
    }

    /**
     * Method to know how many points the owner has.
     *
     * @return
     *          Number of points in the wallet.
     */
    public int getPoints()
    {
        return _playerPoints;
    }

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
    public boolean isThemePurchased(Themes theme)
    {
        return _purchasedThemesList.contains((theme));
    }

    /**
     * Method to buy a theme.
     * It also purchases the skins associated to that theme
     *
     * @param theme
     *              Theme to be bought.
     */
    public void buyTheme(Themes theme)
    {
        if(!isThemePurchased(theme))
        {
            _purchasedThemesList.add(theme);

            /*
            TODO: MARCAR LAS SKINS CORRESPONDIENTES AL TEMA COMO COMPRADAS
             */
        }
    }

    /**
     * Method to know how much a theme costs
     *
     * @param theme
     *              The theme in question.
     * @return
     *              The number of points it costs.
     */
    public int getThemeCost(Themes theme)
    {
        /*
        TODO: Esperando a la contestación en issues a ver que hago :)
         */
        return 0;
    }

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
    public boolean isPlayerSkinPurchased (PlayerSkins skin)
    {
        return _purchasedPlayerSkinsList.contains(skin);
    }

    /**
     * Method to buy a certain player skin.
     *
     * @param skin
     *              Skin to be bought.
     */
    public void buyPlayerSkin(PlayerSkins skin)
    {
        if(!isPlayerSkinPurchased(skin))
        {
            _purchasedPlayerSkinsList.add(skin);
        }
    }

    /**
     * Method to know how much a certain skin costs.
     *
     * @param skin
     *              The skin in question.
     * @return
     *              The price of the skin.
     */
    public int getPlayerSkinCost (PlayerSkins skin)
    {
        return 0;
    }

}
