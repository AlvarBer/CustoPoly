package com.iplusplus.custopoly.model;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.Resources.Theme;
import android.util.Xml;

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
    //List containing the available themes in shop
    private ArrayList<Theme> themesInShop;
    //List containing the player purchased themes
    private ArrayList<Theme> _purchasedThemesList;
    //List containing the player purchased skins
    private ArrayList<PlayerSkins> _purchasedPlayerSkinsList;
    
    //Private auxiliary class for readng the xml for the themes
    private class shopXMLParser
    {
    	private XmlPullParser _parser;
    	public shopXMLParser(String file)
    	{
    		
    		XmlPullParser _parser = Xml.newPullParser();
    		_parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
    		InputStream in = new FileInputStream(new FILE(file));
    		_parser.setInput(in, null);
    		_parser.nextTag();
    		
    		readXmlShopFile();
    	}
    	
    	private void readXmlShopFile()
    	{
    		//Require to start with this tag
    		_parser.require(XmlPullParser.START_TAG, null, "shop");
    		//Loop until we reach the end tag
    		while(_parser.next() != XmlPullParser.END_TAG)
    		{
    			//Dont read the xml until we reach the previous defined start point
    			if(_parser.getEventType() != XmlPullParser.START_TAG)
    			{
    				continue;
    			}
    			
    			//Look for all themes and process each one
    			String tag = _parser.getName();
    			
    			if(tag.equals("theme"))
    			{
    				readTheme();
    			}
    		
    		}
    	}
    	
    	private void readTheme()
    	{
    		private String name = "";
			private float price = 0.0;
			private String backgroundPath = "";
			private String communityBoxCardPath = "";
			private String fortuneCardPath = "";
			private ArrayList<PlayerSkin> playerSkinsList = new ArrayList<PlayerSkin>;
			
    		_parser.require(XmlPullParser.START_TAG, null, "theme");
    		//Read until we find the end
    		while(_parser.next() != XmlPullParser.END_TAG)
    		{
    			//Not read the xml until we reach the previous defined start point
    			if(_parser.getEventType() != XmlPullParser.START_TAG)
    			{
    				continue;
    			}
    			
    			tag = _parser.getName();
    			switch(tag)
    			{
    			case "name":
    	    		_parser.require(XmlPullParser.START_TAG, null, "name");
    				name = _parser.nextText();
    	    		_parser.require(XmlPullParser.END_TAG, null, "name");
    				break;
    			case "price":
    	    		_parser.require(XmlPullParser.START_TAG, null, "price");
    				price = Integer(_parser.nextText());
    	    		_parser.require(XmlPullParser.END_TAG, null, "price");
    				break;
    			case "backgroundPath":
    	    		_parser.require(XmlPullParser.START_TAG, null, "backgroundPath");
    	    		backgroundPath = _parser.nextText();
    	    		_parser.require(XmlPullParser.END_TAG, null, "backgroundPath");
    				break;
    			case "communityBoxCardPath":
    	    		_parser.require(XmlPullParser.START_TAG, null, "communityBoxCardPath");
    	    		communityBoxCardPath = _parser.nextText();
    	    		_parser.require(XmlPullParser.END_TAG, null, "communityBoxCardPath");
    				break;
    			case "fortuneCardPath":
    	    		_parser.require(XmlPullParser.START_TAG, null, "fortuneCardPath");
    	    		fortuneCardPath = _parser.nextText();
    	    		_parser.require(XmlPullParser.END_TAG, null, "fortuneCardPath");
    				break;
    			case "skin":
    	    		_parser.require(XmlPullParser.START_TAG, null, "skin");
    	    		playerSkinList.add(readSkin());
    	    		_parser.require(XmlPullParser.END_TAG, null, "skin");
    				break;
    			default:
    				_parser.nextTag();
    				break;
    			}
    		}
    		
    		themesInShop.add(new Theme(name, price, backgroundPath, communityBoxCardPath, fortuneCardPath, playerSkinsList));
    	}
    	
    	private PlayerSkin readSkin()
    	{
    		String name = "";
    		float price = 0.0;
    		String imagePath = "";
    		
    		while(_parser.next() != XmlPullParser.END_TAG)
    		{
	    		String tag = _parser.getName();;
	    		switch(tag)
	    		{
	    		case "name":
		    		_parser.require(XmlPullParser.START_TAG, null, "name");
					name = _parser.nextText();
		    		_parser.require(XmlPullParser.END_TAG, null, "name");
					break;
				case "price":
		    		_parser.require(XmlPullParser.START_TAG, null, "price");
					price = Integer(_parser.nextText());
		    		_parser.require(XmlPullParser.END_TAG, null, "price");
					break;
				case "backgroundPath":
		    		_parser.require(XmlPullParser.START_TAG, null, "imagePath");
		    		imagePath = _parser.nextText();
		    		_parser.require(XmlPullParser.END_TAG, null, "imagePath");
					break;
	    		}
    		}
    		
    		return new PlayerSkin(name, price, imagePath);
    	}
    }


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
    public void buyTheme(Theme theme)
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
    public int getThemeCost(Theme theme)
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
    
    private void savePurchasedThemes(String file)
    {
    	PrintWriter writer = new PrintWriter(file, "UTF-8");
    	for(Theme t: _purchasedThemeList)
    	{
    		writer.println(t.getName());
    	}
    	
    	writer.println("SkinsList:");
    	
    	for(PlayerSkin s: _purchasedPlayerSkins)
    	{
    		writer.println(s.getName());
    	}
    	
    	writer.close();
    }
    
    private void loadPurchasedThemes(String file)
    {
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	
    	String line = br.readLine();
    	
    	while(!line.equals("SkinsList:"))
    	{
    		Theme theme;
    		for(Theme t: themesInShop)
    		{
    			if(t.getName().equals(line))
    			{
    				theme = t;
    				break;
    			}
    		}
    		
    		_purchasedThemesList.add(theme);
    		
    		line = br.readLine();
    	}
    	
    	line = br.readLine();
    	
    	while(line != null)
    	{
    		
    	}
    }

}
