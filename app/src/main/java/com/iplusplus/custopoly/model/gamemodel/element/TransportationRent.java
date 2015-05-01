package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public class TransportationRent extends Rent implements Serializable {

	public TransportationRent(int baseRent) {
		super(baseRent);
	}
	
	public int getRent(int transportationAmount) {
		return getBaseRent() * transportationAmount;
	}

}
