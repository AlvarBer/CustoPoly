package com.iplusplus.custopoly.model;

import android.util.Xml;
import com.iplusplus.custopoly.Custopoly;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.util.HashSet;

/**
 * SINGLETON class that has to:
 *          Keep track of the {outsideofgame} points of the player.
 *          Keep track of the themes the player has bought.
 *          Keep a list of prices for each item.
 */
public class ShopKeeper {

    //TODO DECIDIR SI LO HACEMOS SINGLETON O NO -- SI SE HACE PROBLEMA CON EL PARAMETRO METIDO EN EL COSNTRUCTOR AL CREAR EL SINGLETON..
    // private static ShopKeeper INSTANCE;

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of ShopKeeper that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    /*
    public static ShopKeeper getInstance()
    {
        if(INSTANCE == null)
        {
            return new ShopKeeper();
        }
        return INSTANCE;
    }
   */


    //Variable that keeps track of the player points
    private int playerPoints;
    //Set containing the available themes in shop
    private HashSet<GameTheme> themesInShopList;
    //Set containing the available skins in shop
    private HashSet<PlayerSkin> skinsInShopList;
    //Set containing the player purchased themes
    private HashSet<GameTheme> purchasedThemesList;
    //Set containing the player purchased skins
    private HashSet<PlayerSkin> purchasedPlayerSkinsList;

    //TODO:Adapt Parser to the new GameTheme scheme
    //Private auxiliary class for reading the xml for the themes
    private class shopXMLParser
    {
        private XmlPullParser xmlParser;

        /***
         * Constructor that reads, parse and add all the themes and skins at the shop into the corresponding lists
         * @param file Name of the xml file (with the path, not just the name)
         */
        public shopXMLParser(String file)
        {
            //Stream for reading from xml file
            InputStream in = null;
            try {
                //Create the parser
                xmlParser = Xml.newPullParser();

                //Read the file and send it to the parser
                in  = Custopoly.getAppContext().getAssets().open(file);
                xmlParser.setInput(in, null);

                //Search first tag in the file
                xmlParser.nextTag();

                //Process the xml tokens
                readXmlShopFile();
            }
            catch(Exception e)
            {
            }
            finally
            {
                //Try to close the file (can be null so nullPtrException)
                try
                {
                    in.close();
                }
                catch(Exception e)
                {
                }

            }
        }

        /***
         * Main method of the class. Reads and process all the tokens in the xml and adds the themes/skin to the shop lists
         * @throws IOException
         * @throws XmlPullParserException
         */
        private void readXmlShopFile() throws IOException, XmlPullParserException {
            //Require to start with this tag
            xmlParser.require(XmlPullParser.START_TAG, null, "assets");
            //Loop until we reach the end tag
            while(xmlParser.next() != XmlPullParser.END_TAG)
            {
                //Dont read the xml until we reach the previous defined start point
                if(xmlParser.getEventType() != XmlPullParser.START_TAG)
                {
                    continue;
                }

                //Look for all themes and process each one
                String tag = xmlParser.getName();
                if(tag.equals("theme"))
                {
                    readTheme();
                }

            }
        }

        /***
         * Reads the theme information from the xml and copy it to the shopThemeList
         * @throws IOException
         * @throws XmlPullParserException
         */
        private void readTheme() throws IOException, XmlPullParserException
        {
            //Variables to load the themes
            String name = "";
            double price = 0.0;
            String backgroundPath = "";
            String communityBoxCardPath = "";
            String fortuneCardPath = "";
            String boardDataPath = "";
            String cardsDataPath = "";
            HashSet<PlayerSkin> themeSkinsList = new HashSet<>();

            //Set the new starting tag
            xmlParser.require(XmlPullParser.START_TAG, null, "theme");
            //Read until we find the end
            while(xmlParser.next() != XmlPullParser.END_TAG)
            {
                //Dont read the xml until we reach the previous defined start point
                if(xmlParser.getEventType() != XmlPullParser.START_TAG)
                {
                    continue;
                }

                String tag;
                tag = xmlParser.getName();
                switch(tag)
                {
                    case "name":
                        //Change the starting point and read the text inside it
                        xmlParser.require(XmlPullParser.START_TAG, null, "name");
                        name = xmlParser.nextText();
                        xmlParser.require(XmlPullParser.END_TAG, null, "name");
                        break;
                    case "price":
                        //Change the starting point and read the text inside it
                        xmlParser.require(XmlPullParser.START_TAG, null, "price");
                        price = Double.parseDouble(xmlParser.nextText());
                        xmlParser.require(XmlPullParser.END_TAG, null, "price");
                        break;
                    case "skin":
                        //Change the starting point and read the tags inside it. Then add it to the skins list
                        xmlParser.require(XmlPullParser.START_TAG, null, "skin");
                        themeSkinsList.add(readSkin());
                        xmlParser.require(XmlPullParser.END_TAG, null, "skin");
                        break;
                    default:
                        //Skip tag and read next one
                        xmlParser.nextTag();
                        break;
                }
            }

            //Save the theme
            themesInShopList.add(new GameTheme(name, price, themeSkinsList));
        }

        /***
         * Reads the skin information inside the Theme. It adds the skin into skinsInShopList and returns the skin
         * @return Return the skin readed
         * @throws IOException
         * @throws XmlPullParserException
         */
        private PlayerSkin readSkin() throws IOException, XmlPullParserException {
            //Variables for creating the theme
            String name = "";
            double price = 0.0;
            String imageResName = null;

            while (xmlParser.next() != XmlPullParser.END_TAG) {

                if(xmlParser.getEventType() != XmlPullParser.START_TAG)
                {
                    continue;
                }

                String tag = xmlParser.getName();
                switch(tag)
                {
                    case "name":
                        xmlParser.require(XmlPullParser.START_TAG, null, "name");
                        name = xmlParser.nextText();
                        xmlParser.require(XmlPullParser.END_TAG, null, "name");
                        break;
                    case "price":
                        xmlParser.require(XmlPullParser.START_TAG, null, "price");
                        price = Double.parseDouble(xmlParser.nextText());
                        xmlParser.require(XmlPullParser.END_TAG, null, "price");
                        break;
                    case "backgroundPath":
                        xmlParser.require(XmlPullParser.START_TAG, null, "backgroundPath");
                        imageResName = xmlParser.nextText();
                        xmlParser.require(XmlPullParser.END_TAG, null, "backgroundPath");
                        break;
                }
            }

            PlayerSkin skin = new PlayerSkin(name, price, imageResName);
            skinsInShopList.add(skin);

            return skin;
        }
    }

    /***
     * Constructor that allocates the lists and load the shop information from the xml to them
     * @param filePath xml file path
     */
    public ShopKeeper(String filePath)
    {
        themesInShopList = new HashSet<>();
        skinsInShopList = new HashSet<>();
        purchasedThemesList = new HashSet<>();
        purchasedPlayerSkinsList = new HashSet<>();

        //Load with the parser the shop information
        new shopXMLParser(filePath);
    }

    /**
     * Add some points to the player's wallet.
     *
     * @param points
     *              Number of points to add.
     */
    public void addPoints(int points)
    {
        playerPoints += points;
    }

    /**
     * Method to know how many points the owner has.
     *
     * @return
     *          Number of points in the wallet.
     */
    public int getPoints()
    {
        return playerPoints;
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
    public boolean isThemePurchased(GameTheme theme)
    {
        return purchasedThemesList.contains(theme);
    }

    /**
     * Method to get a purchased theme by name
     *
     * @param themeName
     *              The theme name.
     * @return
     *              The theme with that name. Null if it doesnt exis
     */
    public GameTheme getPurchasedTheme(String themeName)
    {
        for(GameTheme theme: purchasedThemesList)
        {
            if(theme.getName().equals(themeName)) {
                return theme;
            }
        }

        return null;
    }

    /**
     * Method to buy a theme.
     * It also purchases the skins associated to that theme
     *
     * @param theme
     *              GameTheme to be bought.
     */
    public void buyTheme(GameTheme theme)
    {
        if(!isThemePurchased(theme))
        {
            purchasedThemesList.add(theme);
            purchasedPlayerSkinsList.addAll(theme.getPlayerSkinsList());
            playerPoints -= theme.getPrice();
        }
    }

    /**
     * Method to know how much a theme costs
     *
     * @param themeName
     *              The theme name.
     * @return
     *              The number of points it costs.
     */
    public double getThemeCost(String themeName)
    {
        for(GameTheme theme: themesInShopList)
        {
            if(theme.getName().equals(themeName)) {
                return theme.getPrice();
            }
        }

        return 0;
    }

    public HashSet<GameTheme> getPurchasedThemesList()
    {
        return purchasedThemesList;
    }

    public HashSet<GameTheme> getThemesInShopList()
    {
        return themesInShopList;
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
    public boolean isPlayerSkinPurchased (PlayerSkin skin)
    {
        return purchasedPlayerSkinsList.contains(skin);
    }

    /**
     * Method to get a purchased skin by name
     *
     * @param skinName
     *              The Skin name.
     * @return
     *              The Skin with that name. Null if it doesnt exists
     */
    public PlayerSkin getPurchasedSkin(String skinName)
    {
        for(PlayerSkin skin: purchasedPlayerSkinsList)
        {
            if(skin.getName().equals(skinName)) {
                return skin;
            }
        }

        return null;
    }

    /**
     * Method to buy a certain player skin.
     *
     * @param skin
     *              Skin to be bought.
     */
    public void buyPlayerSkin(PlayerSkin skin)
    {
        //Set will not copy an already purchased item again
        purchasedPlayerSkinsList.add(skin);
    }

    /**
     * Method to know how much a certain skin costs.
     *
     * @param skinName
     *              The skin name.
     * @return
     *              The price of the skin.
     */
    public double getPlayerSkinCost(String skinName)
    {
        for(PlayerSkin skin: skinsInShopList)
        {
            if(skin.getName().equals(skinName)) {
                return skin.getPrice();
            }
        }

        return 0;
    }

    /***
     * Saves the purchased themes and skins of the player in a file
     * @param file File path/name to save
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    //TODO Encriptar y decidir si capturar excepciones o hacer throw (como esta actualemte)
    private void savePurchasedThemesAndSkins(String file) throws FileNotFoundException, UnsupportedEncodingException
    {
        //Crete/Overwrite the new file
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        //Save all the purchased theme names
        for(GameTheme t: purchasedThemesList)
        {
            writer.println(t.getName());
        }
        //Now separete them from the skins purchased
        writer.println("SkinsList:");
        //Save all the purchased skins
        for(PlayerSkin s: purchasedPlayerSkinsList)
        {
            writer.println(s.getName());
        }

        writer.close();
    }

    /**
     * Loads the file containing the saved purchased themes and skins of the player
     * @param file File path/name to load
     * @throws IOException
     */
    //TODO Desencriptar y decidir si capturar excepciones o hacer throw (como esta actualemte)
    private void loadPurchasedThemesAndSkins(String file) throws IOException
    {
        //Load the file
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Read all the themes until we reach the skin section
        String line = br.readLine();
        while(!line.equals("SkinsList:"))
        {
            GameTheme theme = null;
            for(GameTheme t: themesInShopList)
            {
                if(t.getName().equals(line))
                {
                    theme = t;
                    break;
                }
            }

            if(theme != null) {
                purchasedThemesList.add(theme);
            }

            line = br.readLine();
        }

        //Read all the skins until we reach the end
        line = br.readLine();
        while(line != null)
        {
            PlayerSkin skin = null;

            for(PlayerSkin s: skinsInShopList)
            {
                if(s.getName().equals(line))
                {
                    skin = s;
                }
            }

            if(skin != null)
            {
                purchasedPlayerSkinsList.add(skin);
            }
        }

        try
        {
            br.close();
        }
        catch(Exception e)
        {
        }
    }

    public HashSet<PlayerSkin> getPurchasedPlayerSkinsList()
    {
        return purchasedPlayerSkinsList;
    }

    public HashSet<PlayerSkin> getSkinsInShopList()
    {
        return skinsInShopList;
    }

}