package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.GetPaidCommand;

public class Start extends SpecialLand {
	
	public Start() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new GetPaidCommand());
	}

	@Override
	public String getName() {
		return "Start";
	}

}
