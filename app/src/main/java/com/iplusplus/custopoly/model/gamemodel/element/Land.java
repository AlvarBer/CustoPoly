package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.Command;
import com.iplusplus.custopoly.model.gamemodel.command.EmptyCommand;

public abstract class Land {

	private ConstructionAllowance constructionBehavior;
	private Command assignment;
	
	public Land() {
		setAssignment(new EmptyCommand());
	}
	
	public abstract String getName();

	public final boolean isConstructionAllowed() {
		return constructionBehavior.isConstructionAllowed();
	}

	public final ConstructionAllowance getConstructionBehavior() {
		return constructionBehavior;
	}

	public final void setConstructionBehavior(ConstructionAllowance constructionBehavior) {
		this.constructionBehavior = constructionBehavior;
	}
	
	public final void setAssignment(Command assign) {
		assignment = assign;
	}
	
	public final Command getAssignment() {
		return assignment;
	}

}
