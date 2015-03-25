package com.iplusplus.custopoly.model.gamemodel.util;

import android.graphics.Color;
import com.iplusplus.custopoly.model.gamemodel.element.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BoardFactory {

	private static final String LAND_START = "Start";
	private static final String LAND_GOJAIL = "GoJail";
	private static final String LAND_JAIL = "Jail";
	private static final String LAND_FREEPARKING = "FreeParking";
	private static final String LAND_PAYTAX = "PayTax";
	private static final String LAND_CHANCE = "Chance";
	private static final String LAND_COMMUNITYCHEST = "CommunityChest";
	private static final String LAND_COLOREDLAND = "ColoredLand";
	private static final String LAND_TRANSPORTATION = "Transportation";
	private static final String LAND_INFRASTRUCTURE = "Infrastructure";

	public static Board readBoard(File file) {
		Board board = new Board();

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String landInfo = scanner.nextLine();
				if (!landInfo.isEmpty()) {
					addLand(board, generateLandData(landInfo));
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File : " + file.getAbsolutePath() + " NOT FOUND!");
		}

		return board;
	}

	private static ArrayList<String> generateLandData(String landInfo) {
		StringTokenizer tokenizer = new StringTokenizer(landInfo, ",");

		ArrayList<String> landData = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			landData.add(tokenizer.nextToken());
		}
		return landData;
	}

	private static void addLand(Board board, ArrayList<String> landData) {
		String landType = getLandType(landData);

		if (landType.equals(LAND_START)) {
			board.getLands().add(new Start());
		} else if (landType.equals(LAND_GOJAIL)) {
			board.getLands().add(new GoJail());
		} else if (landType.equals(LAND_JAIL)) {
			board.getLands().add(new Jail());
		} else if (landType.equals(LAND_FREEPARKING)) {
			board.getLands().add(new FreeParking());
		} else if (landType.equals(LAND_PAYTAX)) {
			board.getLands().add(new PayTax(getTaxValue(landData, 2)));
		} else if (landType.equals(LAND_CHANCE)) {
			board.getLands().add(new Chance());
		} else if (landType.equals(LAND_COMMUNITYCHEST)) {
			board.getLands().add(new CommunityChest());
		} else if (landType.equals(LAND_COLOREDLAND)) {
			board.getLands().add(
					new ColoredLand(getLandName(landData),
							getLandColor(landData), getLandPrice(landData),
							getHousePrice(landData),
							getColoredLandRentInfo(landData)));
		} else if (landType.equals(LAND_TRANSPORTATION)) {
			board.getLands().add(
					new TransportationLand(getLandName(landData),
							getLandPrice(landData),
							getTransportationLandRentInfo(landData)));
		} else if (landType.equals(LAND_INFRASTRUCTURE)) {
			board.getLands().add(
					new InfrastructureLand(getLandName(landData),
							getLandPrice(landData),
							getInfrastructureLandRentInfo(landData)));
		} else {
			throw new RuntimeException("There is no land type named : "	+ landType);
		}

	}

	private static String getLandName(ArrayList<String> landData) {
		String name = getValueAt(landData, 1);
		return name;
	}

	private static Color getLandColor(ArrayList<String> landData) {
        //TODO: MAKE THIS RETURN THE APPROPRIATE COLOR.
        //return Color.parseColor(getValueAt(landData, 4));
        return new Color();
	}

	private static int getLandPrice(ArrayList<String> landData) {
		int landPrice = Integer.parseInt(getValueAt(landData, 2));
		return landPrice;
	}

	private static int getHousePrice(ArrayList<String> landData) {
		int housePrice = Integer.parseInt(getValueAt(landData, 11));
		return housePrice;
	}
	
	private static Rent getColoredLandRentInfo(ArrayList<String> landData) {
		ColoredLandRent rent = new ColoredLandRent(Integer.parseInt(getValueAt(landData, 3)));
		rent.setHouseRent(1, Integer.parseInt(getValueAt(landData, 5)));
		rent.setHouseRent(2, Integer.parseInt(getValueAt(landData, 6)));
		rent.setHouseRent(3, Integer.parseInt(getValueAt(landData, 7)));
		rent.setHouseRent(4, Integer.parseInt(getValueAt(landData, 8)));
		rent.setHotelRent(Integer.parseInt(getValueAt(landData, 9)));
		return rent;
	}

	private static String getLandType(ArrayList<String> landData) {
		return getValueAt(landData, 0);
	}

	private static int getTaxValue(ArrayList<String> landData, int index) {
		return Integer.parseInt(getValueAt(landData, index));
	}

	private static String getValueAt(ArrayList<String> landData, int index) {
		return landData.get(index);
	}
	
	private static Rent getTransportationLandRentInfo(ArrayList<String> landData) {
		TransportationRent rent = new TransportationRent(
				Integer.parseInt(getValueAt(landData, 3)));
		return rent;
	}

	private static Rent getInfrastructureLandRentInfo(ArrayList<String> landData) {
		InfrastructureRent rent = new InfrastructureRent(
				Integer.parseInt(getValueAt(landData, 3)),
				Integer.parseInt(getValueAt(landData, 4)));
		return rent;
	}

}
