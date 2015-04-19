package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

import java.io.Serializable;

public abstract class PropertyLand extends Land implements Serializable {

	private String name;
	private int price;
	private Rent rentInfo;

	public PropertyLand(String name, int price, Rent rent) {
		setName(name);
		setPrice(price);
		setRent(rent);
	}

	private final void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	private final void setPrice(int price) {
		this.price = price;
	}

	public void setRent(Rent rent) {
		this.rentInfo = rent;
	}

	public final int getPrice() {
		return price;
	}
	
	public final Rent getRentInfo() {
		return rentInfo;
	}
	
	public final int getMortgage() {
		return this.price / 2;
	}
	
	public abstract int acceptCalculator(RentCalculator calculator);
}
