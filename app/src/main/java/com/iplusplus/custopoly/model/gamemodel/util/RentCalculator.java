package com.iplusplus.custopoly.model.gamemodel.util;


import com.iplusplus.custopoly.model.gamemodel.element.*;

public class RentCalculator {

    private Game game;

    public RentCalculator(Game game) {
        this.game = game;
	}

	public int calculate(ColoredLand land){
		ColoredLandRent rent = (ColoredLandRent) land.getRentInfo();
		if(land.getBuildingHolder().size() > 0) {
			if (land.getBuildingHolder().getHotelAmount() > 0) {
				return rent.getHotelRent();
			} else {
				return rent.getHouseRent(land.getBuildingHolder().getHouseAmount());
			}
		} else if (isPlayerHasAll(land)) {
			return 2 * rent.getBaseRent();
		} else {
			return rent.getBaseRent();
		}
	}

	private boolean isPlayerHasAll(ColoredLand land) {
		return !(checkOtherPlayers(land) || checkBank(land));
	}

	private boolean checkOtherPlayers(ColoredLand land) {
		for (Player player : game.getPlayers()) {
			if(!player.equals(game.getOwner(land))) {
				for (PropertyLand property : player.getProperties()) {
					if(property instanceof ColoredLand && hasSameColor(land, (ColoredLand) property))
						return false;
				}
			}
		}
		return true;
	}

	private boolean checkBank(ColoredLand land) {
		for (PropertyLand property : game.getBank().getProperties()) {
			if(property instanceof ColoredLand && hasSameColor(land, (ColoredLand) property))
				return false;
		}
		return true;
	}

	private boolean hasSameColor(ColoredLand land1, ColoredLand land2) {
		return land1.getColor() == land2.getColor();
	}
	
	public int calculate(InfrastructureLand land){
		InfrastructureRent rent = (InfrastructureRent) land.getRentInfo();
		int amount = 0;
		for (PropertyLand property : game.getOwner(land).getProperties()) {
			if (property instanceof InfrastructureLand) {
				amount++;
			}
		}
		return rent.getDicedRent(DicePair.getDiceValue(), amount);
	}

	public int calculate(TransportationLand land){
		TransportationRent rent = (TransportationRent) land.getRentInfo();
		int amount = 0;
		for (PropertyLand property : game.getOwner(land).getProperties()) {
			if (property instanceof TransportationLand) {
				amount++;
			}
		}
		return rent.getRent(amount);
	}
}
