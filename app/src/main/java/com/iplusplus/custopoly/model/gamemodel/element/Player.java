package com.iplusplus.custopoly.model.gamemodel.element;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> cardsOwned;
	private ArrayList<PropertyLand> propertiesOwned;
	private ArrayList<PropertyLand> propertiesMortgaged;
	private int playerID, playerBalance;
	private String playerName;
	private Token token;

	public Player(int playerID, String playerName,String tokenName) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.playerBalance = Game.INITIAL_PAYMENT;
		this.propertiesOwned = new ArrayList<PropertyLand>();
		this.propertiesMortgaged = new ArrayList<PropertyLand>();
		this.token = new Token(tokenName);
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

	public Token getToken() {
		return token;
	}
	
	
}
