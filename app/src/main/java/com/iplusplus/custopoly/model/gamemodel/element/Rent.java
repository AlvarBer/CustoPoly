package com.iplusplus.custopoly.model.gamemodel.element;

public abstract class Rent {
	
	private int baseRent;
	
	public Rent(int baseRent) {
		setBaseRent(baseRent);
	}

	public final void setBaseRent(int rent) {
		this.baseRent = rent;
	}

	public final int getBaseRent() {
		return baseRent;
	}

}
