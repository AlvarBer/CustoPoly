package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public class Hotel extends Building implements Serializable {

	@Override
	public boolean isHouse() {
		return false;
	}

	@Override
	public boolean isHotel() {
		return true;
	}

}
