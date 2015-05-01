package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public class InfrastructureRent extends Rent implements Serializable {
	
	private int wholeRent; // used if you have all the infrastructures

	public InfrastructureRent(int baseRent, int wholeRent) {
		super(baseRent);
		setWholeRent(wholeRent);
	}
	
	public int getDicedRent(int diceValue, int infrastructureAmount) {
		if (infrastructureAmount == 1) {
			return getBaseRent() * diceValue;
		} else if (infrastructureAmount == 2) {
			return getWholeRent() * diceValue;
		}
		throw new RuntimeException("Illegal Infrastructure Amount.");
	}

	private int getWholeRent() {
		return wholeRent;
	}

	private void setWholeRent(int wholeRent) {
		this.wholeRent = wholeRent;
	}

}
