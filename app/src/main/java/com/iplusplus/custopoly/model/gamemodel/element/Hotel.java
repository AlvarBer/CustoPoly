package com.iplusplus.custopoly.model.gamemodel.element;

public class Hotel extends Building {

	@Override
	public boolean isHouse() {
		return false;
	}

	@Override
	public boolean isHotel() {
		return true;
	}

}
