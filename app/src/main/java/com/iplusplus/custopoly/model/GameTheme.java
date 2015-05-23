package com.iplusplus.custopoly.model;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Kerith on 23/03/2015.
 */
public class GameTheme implements Serializable {
   
	private String name;
	private double price;
	private String backgroundPathResource;
    private String communityBoxCardPathResource;
    private String fortuneCardPathResource;
    private String boardDataPath;
    private String cardsDataPath;
    private HashSet<PlayerSkin> playerSkinsList;

	/***
     * Constructor to create the theme given all the data needed
     * @param name Name of the theme
     * @param price Price in euros
     * @param backgroundPathResource Path where the background image is
     * @param communityBoxCardPathResource Path where the community box card is
     * @param fortuneCardPathResource Path where the fortune card path is
     * @param playerSkinsList List with the skins attached to the theme
     */

	public GameTheme(String name, double price, String backgroundPathResource, String communityBoxCardPathResource,
                     String fortuneCardPathResource, String boardDataPath, String cardsDataPath, HashSet<PlayerSkin> playerSkinsList) {
		this.name = name;
		this.price = price;
		this.backgroundPathResource = backgroundPathResource;
		this.communityBoxCardPathResource = communityBoxCardPathResource;
		this.fortuneCardPathResource = fortuneCardPathResource;
		this.boardDataPath = boardDataPath;
		this.cardsDataPath = cardsDataPath;
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
		return backgroundPathResource;
	}

	public String getCommunityBoxCardPathResource() {
		return communityBoxCardPathResource;
	}

	public String getFortuneCardPathResource() {
		return fortuneCardPathResource;
	}

	public String getCardsDataPath() {
		return cardsDataPath;
	}

	public String getBoardDataPath() {
		return boardDataPath;
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


