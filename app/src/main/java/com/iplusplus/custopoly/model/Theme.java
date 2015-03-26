package com.iplusplus.custopoly.model;

import java.util.ArrayList;

/**
 * Created by Kerith on 23/03/2015.
 */
public class Theme {
   
	private String name;
	private float price;
	private String backgroundPath;
	private String communityBoxCardPath;
	private String fortuneCardPath;
	private ArrayList<PlayerSkins> playerSkinsList;
	
	public Themes(String name, float price, String background, String communityBoxCard, String fortuneCardPath, ArrayList<PlayerSkins> skinsList)
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
	
	public float getPrice()
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
}
