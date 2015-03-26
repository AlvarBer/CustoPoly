package com.iplusplus.custopoly.model;

/**
 * Created by Kerith on 23/03/2015.
 */
public class PlayerSkin {
   
	private String name;
	private double price;
	private String imagePath;
	
	public PlayerSkin(String name, double price, String imagePath)
	{
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getImagePath()
	{
		return imagePath;
	}
	
}
