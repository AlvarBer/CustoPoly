package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public class ColoredLandRent extends Rent implements Serializable {
	
	private int[] houseRents; 
	private int   hotelRent;

	public ColoredLandRent(int baseRent) {
		super(baseRent);
		houseRents = new int[4];
	}
	
	public final void setHouseRent(int houseAmount, int rent) {
		if (hasLegalHouseAmount(houseAmount)) {
			houseRents[houseAmount - 1] = rent; // -1 to fix in the index
		} else {
			throw new RuntimeException("Illegal House Amount.");
		}
	}
	
	public final int getHouseRent(int houseAmount) {
		if (hasLegalHouseAmount(houseAmount)) {
			return houseRents[houseAmount - 1]; // -1 to fix in the index
		} else {
			throw new RuntimeException("Illegal House Amount.");			
		}
	}

	private boolean hasLegalHouseAmount(int houseAmount) {
		return houseAmount > 0 && houseAmount <= houseRents.length;
	}
	
	public final void setHotelRent(int rent) {
		hotelRent = rent;
	}
	
	public final int getHotelRent() {
		return hotelRent;
	}	

}
