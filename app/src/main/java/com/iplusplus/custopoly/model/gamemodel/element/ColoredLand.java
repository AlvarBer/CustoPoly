package com.iplusplus.custopoly.model.gamemodel.element;


import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.PurchasableCommand;
import com.iplusplus.custopoly.model.gamemodel.util.BuildingHolder;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

import java.io.Serializable;

public class ColoredLand extends PropertyLand implements Serializable {
	
	private BuildingHolder buildingHolder;
	private int color;
	private int housePrice;
	
	public ColoredLand(String name, int color, int price, int housePrice, Rent rent, int landIndex) {
		super(name, price, rent, landIndex);
		this.housePrice = housePrice;
		setColor(color);
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_ALLOWED);
		initilizeBuildingHolder();
		setAssignment(new PurchasableCommand());
	}


	private void initilizeBuildingHolder() {
		buildingHolder = new BuildingHolder();		
	}

	public BuildingHolder getBuildingHolder() {
		return buildingHolder;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getHousePrice() {
		return housePrice;
	}

	public int getHotelPrice() {
		return housePrice * 5;
	}

	@Override
	public int acceptCalculator(RentCalculator calculator) {
		return calculator.calculate(this);
	}

}
