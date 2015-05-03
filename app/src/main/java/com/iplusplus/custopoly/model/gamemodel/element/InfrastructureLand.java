package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.PurchasableCommand;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

import java.io.Serializable;

public class InfrastructureLand extends PropertyLand implements Serializable {
	
	public InfrastructureLand(String name, int price, Rent rent, int landIndex) {
		super(name, price, rent, landIndex);
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new PurchasableCommand());
	}

	@Override
	public int acceptCalculator(RentCalculator calculator){
		return calculator.calculate(this);
	}

}
