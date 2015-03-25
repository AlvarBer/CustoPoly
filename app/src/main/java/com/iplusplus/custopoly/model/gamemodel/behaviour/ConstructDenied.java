package com.iplusplus.custopoly.model.gamemodel.behaviour;

public class ConstructDenied implements ConstructionAllowance {

	@Override
	public boolean isConstructionAllowed() {
		return false;
	}

}
