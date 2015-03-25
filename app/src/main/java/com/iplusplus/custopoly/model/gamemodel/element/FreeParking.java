package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;

public class FreeParking extends SpecialLand {
	
	public FreeParking() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
	}

	@Override
	public String getName() {
		return "FreeParking";
	}

}
