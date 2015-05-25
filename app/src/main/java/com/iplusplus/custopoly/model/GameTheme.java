package com.iplusplus.custopoly.model;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Kerith on 23/03/2015.
 */
public class GameTheme implements Serializable {
   
	private String name;
	private double price;
    private HashSet<PlayerSkin> playerSkinsList;

	/***
     * Constructor to create the theme given all the data needed
     * @param name Name of the theme
     * @param price Price in euros
     * @param playerSkinsList List with the skins attached to the theme
     */

	public GameTheme(String name, double price, HashSet<PlayerSkin> playerSkinsList) {
		this.name = name;
		this.price = price;
		this.playerSkinsList = playerSkinsList;

	}

	public String getName()
	{
		return name;
	}

	public double getPrice()
	{
		return price;
	}

	public String getBackgroundPathResource() {
		return this.name + "_board";
	}

	public String getCommunityBoxCardPathResource() {
		return this.name + "_community_background";
	}

	public String getFortuneCardPathResource() {
		return this.name + "_chance_background";
	}

	public String getCardsDataPath() {
		return this.name + "_cards";
	}

	/***
     * Given a name of the skin return the skin inside the theme skins list. Null if it doesn't exist.
     * @param name Name of the skin to search
     * @return null if not found PlayerSkin if found
     */
	public PlayerSkin getPlayerSkinInTheme(String name)
	{
		for(PlayerSkin skin: playerSkinsList)
		{
			if(skin.getName().equals(name))
			{
				return skin;
			}
		}

		return null;
	}

    /***
     * Get the skin list
     * @return HashSet<PlayerSkin>
     */
    public HashSet<PlayerSkin> getPlayerSkinsList()
    {
        return this.playerSkinsList;
    }

    //get the object
    public GameTheme getGameTheme () {
        return this;
    }
    /***
     * Define if two GameThemes are equal looking at his names (useful for AbstractCollections
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof GameTheme)
        {
            GameTheme t = (GameTheme)o;
            return this.name.equals(t.name);
        }
        else
        {
            return false;
        }
    }

}


