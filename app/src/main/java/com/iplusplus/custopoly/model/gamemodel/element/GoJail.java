package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.GoJailCommand;

import java.io.Serializable;

public class GoJail extends SpecialLand implements Serializable {
	
	public GoJail() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new GoJailCommand());
	}

	@Override
	public String getName() {
		return "GoJail";
	}

}
