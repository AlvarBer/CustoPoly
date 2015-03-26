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

    public HashSet<PlayerSkin> getPlayerSkinsList()
    {
        return getPlayerSkinsList();
    }
}
