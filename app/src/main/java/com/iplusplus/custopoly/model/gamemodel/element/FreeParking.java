package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;

import java.io.Serializable;

public class FreeParking extends SpecialLand implements Serializable {
	
	public FreeParking() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
	}

	@Override
	public String getName() {
		return "FreeParking";
	}

}
