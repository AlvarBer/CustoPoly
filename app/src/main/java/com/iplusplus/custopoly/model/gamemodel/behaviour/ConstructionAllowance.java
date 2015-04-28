package com.iplusplus.custopoly.model.gamemodel.behaviour;

import java.io.Serializable;

public interface ConstructionAllowance extends Serializable {

    public static final ConstructionAllowance CONSTRUCTION_ALLOWED = new ConstructAllowed();
	public static final ConstructionAllowance CONSTRUCTION_DENIED = new ConstructDenied();
	
	public abstract boolean isConstructionAllowed();

}
