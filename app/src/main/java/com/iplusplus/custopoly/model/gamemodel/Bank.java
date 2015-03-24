package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.gamemodel.cellelements.Hotel;
import com.iplusplus.custopoly.model.gamemodel.cellelements.House;
import com.iplusplus.custopoly.model.gamemodel.cellelements.PropertyLand;

import java.util.List;

/**
 * Keeps track of how many
 * Houses, Hotels, Cards and PropertyLands
 * there are left to buy.
 */
public class Bank {

    private List<House> houses;
    private List<Hotel> hotels;
    private List<Card> cards;
    private List<PropertyLand> propertyLands;

}
