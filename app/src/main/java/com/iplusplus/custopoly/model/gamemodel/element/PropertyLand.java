package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

import java.io.Serializable;

public abstract class PropertyLand extends Land implements Serializable {

	private String name;
	private int price;
    private int landIndex;
	private Rent rentInfo;

	public PropertyLand(String name, int price, Rent rent, int landIndex) {
		setName(name);
		setPrice(price);
		setRent(rent);
        setLandIndex(landIndex);
	}

	private final void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	private void setPrice(int price) {
		this.price = price;
	}

	public void setRent(Rent rent) {
		this.rentInfo = rent;
	}

    public void setLandIndex(int index) {this.landIndex = index;}

	public final int getPrice() {
		return price;
	}
	
	public final Rent getRentInfo() {
		return rentInfo;
	}

    public final int getLandIndex() {return landIndex;}
	
	public final int getMortgage() {
		return this.price / 2;
	}
	
	public abstract int acceptCalculator(RentCalculator calculator);
}
