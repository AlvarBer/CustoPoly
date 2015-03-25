package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.DrawCommunityCommand;

public class CommunityChest extends SpecialLand {
	
	public CommunityChest() {
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new DrawCommunityCommand());
	}

	@Override
	public String getName() {
		return "CommunityChest";
	}

}
