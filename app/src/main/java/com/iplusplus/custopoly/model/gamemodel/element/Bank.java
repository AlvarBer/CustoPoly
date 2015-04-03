package com.iplusplus.custopoly.model.gamemodel.element;


import com.iplusplus.custopoly.model.gamemodel.util.BuildingHolder;

import java.io.Serializable;
import java.util.ArrayList;

public class Bank implements Serializable {
	private ArrayList<PropertyLand> propertyLands;
	public ArrayList<Card> communityCards = new ArrayList<>(); //TODO:
	public ArrayList<Card> chanceCards = new ArrayList<>(); //TODO:
	
	private final int MAX_HOUSE_AMOUNT = 32;
	private final int MAX_HOTEL_AMOUNT = 12;
	
	private ArrayList<House> houses;
	private  ArrayList<Hotel> hotels;
	
	public Bank(ArrayList<PropertyLand> propertyLands){
		this.propertyLands = propertyLands;
		initBuildings();
	}
	
	private void initBuildings() {
		initHouses();
		initHotels();		
	}

	private void initHouses() {
		houses = new ArrayList<House>();
		for (int i = 0; i < MAX_HOUSE_AMOUNT; i++)
			houses.add(new House());
	}

	private void initHotels() {
		hotels = new ArrayList<Hotel>();
		for (int i = 0; i < MAX_HOTEL_AMOUNT; i++)
			hotels.add(new Hotel());
	}

	public void sell(PropertyLand land, Player player){
		propertyLands.remove(land);
		player.addProperty(land);
		player.decreaseBalance(land.getPrice());
	}
	
	public void pay(Player player, int amount){
		player.increaseBalance(amount);
	}
	
	public void sellHouse(Player player, ColoredLand currentLand){
		player.decreaseBalance(currentLand.getHousePrice());
		BuildingHolder holder = currentLand.getBuildingHolder();
		holder.add(houses.remove(0));
	}
	
	public void sellHotel(Player player, ColoredLand currentLand){
		player.decreaseBalance(currentLand.getHousePrice());
		BuildingHolder holder = currentLand.getBuildingHolder();
		holder.add(hotels.remove(0));
	}
	
	public void buyHouse(Player player, ColoredLand currentLand) {
		player.increaseBalance(currentLand.getHousePrice());
		BuildingHolder holder = currentLand.getBuildingHolder();
		houses.add(holder.removeHouse());
	}
	
	public void buyHotel(Player player, ColoredLand currentLand) {
		player.increaseBalance(currentLand.getHotelPrice());
		BuildingHolder holder = currentLand.getBuildingHolder();
		hotels.add(holder.removeHotel());
	}

	public ArrayList<Card> getCommunityCards() {
		return communityCards;
	}

	public ArrayList<Card> getChanceCards() {
		return chanceCards;
	}
	
	public ArrayList<PropertyLand> getProperties() {
		return propertyLands;
	}
	
}
