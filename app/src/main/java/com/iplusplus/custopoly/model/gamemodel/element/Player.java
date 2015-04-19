package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.PlayerSkin;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    //Functionality fields
    private ArrayList<Card> cardsOwned;
    private ArrayList<PropertyLand> propertiesOwned;
	private ArrayList<PropertyLand> propertiesMortgaged;
	private int playerID, playerBalance;
    private int landIndex; //Position in the internal representation of the board

    //Appearance-realated fields
    private String playerName;
    private PlayerSkin skin;

    public Player(int playerID, String playerName, int landIndex, PlayerSkin skin) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.landIndex = landIndex;
        this.skin = skin;
        this.playerBalance = Game.INITIAL_PAYMENT;
        this.propertiesOwned = new ArrayList<PropertyLand>();
        this.propertiesMortgaged = new ArrayList<PropertyLand>();
	}

	public String getName() {
		return this.playerName;
	}

	public int getPlayerID() {
		return this.playerID;
	}
	
	public void increaseBalance(int amount){
		this.playerBalance += amount;
	}
	
	public void decreaseBalance(int amount){
		this.playerBalance -= amount;
	}
	
	public int getBalance(){
		return this.playerBalance;
	}

	public ArrayList<PropertyLand> getProperties() {
		return this.propertiesOwned;
	}

	public ArrayList<Card> getCardsOwned() {
		return this.cardsOwned;
	}

	public void addProperty(PropertyLand land) {
		this.propertiesOwned.add(land);
	}
		
	public void discardProperty(Land land) {
		this.propertiesOwned.remove(land);
	}
	
	public ArrayList<PropertyLand> getMortgagedProperties() {
		return propertiesMortgaged;
	}

	public void addCard(Card card) {
		this.cardsOwned.add(card);
	}

	public void useCard(Card card) {// TODO implement
		this.cardsOwned.remove(card);
	}

    public PlayerSkin getSkin() {
        return this.skin;
    }

    public void setSkin(PlayerSkin skin) {
        this.skin = skin;
    }

    public int getLandIndex() {
        return landIndex;
    }

    public void setLandIndex(int landIndex) {
        this.landIndex = landIndex;
    }
}
