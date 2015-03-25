package com.iplusplus.custopoly.model.gamemodel.behaviour;

public interface ConstructionAllowance {
	
	public static final ConstructionAllowance CONSTRUCTION_ALLOWED = new ConstructAllowed();
	public static final ConstructionAllowance CONSTRUCTION_DENIED = new ConstructDenied();
	
	public abstract boolean isConstructionAllowed();

}
