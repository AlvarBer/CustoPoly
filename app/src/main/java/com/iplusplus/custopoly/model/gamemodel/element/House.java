package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;

public class House extends Building implements Serializable {

	@Override
	public boolean isHouse() {
		return true;
	}

	@Override
	public boolean isHotel() {
		return false;
	}

}
