package com.iplusplus.custopoly.model;

import java.util.HashSet;

/**
 * Created by Kerith on 23/03/2015.
 */
public class GameTheme {
   
	private String name;
	private double price;
	private String backgroundPath;
	private String communityBoxCardPath;
	private String fortuneCardPath;
	private HashSet<PlayerSkin> playerSkinsList;

    /***
     * Constructor to create the theme given all the data needed
     * @param name Name of the theme
     * @param price Price in euros
     * @param background Path where the background image is
     * @param communityBoxCard Path where the community box card is
     * @param fortuneCardPath Path where the fortune card path is
     * @param skinsList List with the skins attached to the theme
     */
	public GameTheme(String name, double price, String background, String communityBoxCard, String fortuneCardPath, HashSet<PlayerSkin> skinsList)
	{
		this.name = name;
		this.price = price;
		this.backgroundPath = background;
		this.communityBoxCardPath = communityBoxCard;
		this.fortuneCardPath = fortuneCardPath;
		this.playerSkinsList = skinsList;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getBackgroundPath()
	{
		return backgroundPath;
	}
	
	public String getCommunityBoxCardPath()
	{
		return communityBoxCardPath;
	}
	
	public String getFortuneCardPath()
	{
		return fortuneCardPath;
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
