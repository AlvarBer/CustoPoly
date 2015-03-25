package com.iplusplus.custopoly.model.gamemodel.element;

import android.graphics.Color;
import com.iplusplus.custopoly.model.gamemodel.behaviour.ConstructionAllowance;
import com.iplusplus.custopoly.model.gamemodel.command.AskBuyCommand;
import com.iplusplus.custopoly.model.gamemodel.util.BuildingHolder;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

public class ColoredLand extends PropertyLand {
	
	private BuildingHolder buildingHolder;
	private Color color;
	private int housePrice;
	
	public ColoredLand(String name, Color color, int price, int housePrice, Rent rent) {
		super(name, price, rent);
		this.housePrice = housePrice;
		setColor(color);
		setConstructionBehavior(ConstructionAllowance.CONSTRUCTION_ALLOWED);
		initilizeBuildingHolder();
		setAssignment(new AskBuyCommand());
	}

	private void setColor(Color color) {
		this.color = color;
	}

	private void initilizeBuildingHolder() {
		buildingHolder = new BuildingHolder();		
	}

	public BuildingHolder getBuildingHolder() {
		return buildingHolder;
	}
	
	public Color getColor() {
		return color;
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
