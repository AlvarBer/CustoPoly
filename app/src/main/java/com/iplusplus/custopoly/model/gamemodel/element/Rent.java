package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public abstract class Rent implements Serializable {
	
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
