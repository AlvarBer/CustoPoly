package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.GoJailCommand;

public class GoJail extends SpecialLand {
	
	public GoJail() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new GoJailCommand());
	}

	@Override
	public String getName() {
		return "GoJail";
	}

}
