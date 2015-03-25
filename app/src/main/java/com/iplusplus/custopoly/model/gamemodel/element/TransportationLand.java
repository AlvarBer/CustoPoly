package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.AskBuyCommand;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

public class TransportationLand extends PropertyLand {

	public TransportationLand(String name, int price, Rent rent) {
		super(name, price, rent);
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_DENIED);
		setAssignment(new AskBuyCommand());
	}

	@Override
	public int acceptCalculator(RentCalculator calculator) {
		return calculator.calculate(this);
	}

}
