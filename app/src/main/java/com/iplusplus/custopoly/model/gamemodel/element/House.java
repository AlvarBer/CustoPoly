package com.iplusplus.custopoly.model.gamemodel.element;

public class House extends Building {

	@Override
	public boolean isHouse() {
		return true;
	}

	@Override
	public boolean isHotel() {
		return false;
	}

}
