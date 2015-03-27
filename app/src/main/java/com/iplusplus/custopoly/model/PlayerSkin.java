package com.iplusplus.custopoly.model;

/**
 * Created by Kerith on 23/03/2015.
 */
public class PlayerSkin {
   
	private String name;
	private double price;
	private String imagePath;

    /***
     * Contructs a skin with all the information needed
     * @param name Name of the Skin
     * @param price Price in euros
     * @param imagePath Path of the skin iamge
     */
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

    /***
     * Define if two PlayerSkins are equal looking at his names (useful for AbstractCollections
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof PlayerSkin)
        {
            PlayerSkin s = (PlayerSkin)o;
            return this.name.equals(s.name);
        }
        else
        {
            return false;
        }
    }
	
}
