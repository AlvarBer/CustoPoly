package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.DrawChanceCommand;

public class Chance extends SpecialLand {
	
	public Chance() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new DrawChanceCommand());
	}

	@Override
	public String getName() {
		return "Chance";
	}

}
