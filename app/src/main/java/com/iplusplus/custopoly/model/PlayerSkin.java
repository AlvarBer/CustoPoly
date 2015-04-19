package com.iplusplus.custopoly.model;

public class PlayerSkin {
   
	private String name;
	private double price;
    private int imageResourceId;

    /***
     * Contructs a skin with all the information needed
     * @param name Name of the Skin
     * @param price Price in euros
     * @param imageResourceId Path of the skin image: E.G: R.drawable.player_skin_darth_vader
     *                        These should be stored in all 4 drawable folders, each resolution with the same name.
     *                        It's just like strings.xml
     */
    public PlayerSkin(String name, double price, int imageResourceId) {
        this.name = name;
		this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}

    public int getImageResourceId() {
        return imageResourceId;
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
            return this.name.equals(s.name) &&
                    this.imageResourceId == s.imageResourceId &&
                    this.price == s.price &&
                    this.hashCode() == s.hashCode();
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
