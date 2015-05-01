package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.GetPaidCommand;

import java.io.Serializable;

public class Start extends SpecialLand implements Serializable {
	
	public Start() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new GetPaidCommand());
	}

	@Override
	public String getName() {
		return "Start";
	}

}
