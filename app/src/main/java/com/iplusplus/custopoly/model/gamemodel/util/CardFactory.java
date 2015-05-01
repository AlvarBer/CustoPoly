package com.iplusplus.custopoly.model.gamemodel.util;

import com.iplusplus.custopoly.model.gamemodel.command.*;
import com.iplusplus.custopoly.model.gamemodel.element.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public abstract class CardFactory {

	private static final String COMMAND_GETPRIZE = "GetPrizeCommand";
	private static final String COMMAND_MOVELAND = "MoveLandCommand";
	private static final String COMMAND_PAYFEE = "PayFeeCommand";
	private static final String COMMAND_MOVEBACKWARD = "MoveBackwardCommand";
	private static final String COMMAND_DRAWCHANCE = "DrawChanceCommand";
	
	private static final String CARD_CHANCE = "Chance";
	private static final String CARD_COMMUNITYCHEST = "CommunityChest";

	public static ArrayList<Card> readChanceCards(InputStream file) {
		return readCards(file, CARD_CHANCE);
	}

	public static ArrayList<Card> readCommunityCards(InputStream file) {
		return readCards(file, CARD_COMMUNITYCHEST);
	}
	
	private static ArrayList<Card> readCards(InputStream file, String cardType) {
		ArrayList<Card> cards = new ArrayList<Card>();

		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
            String chanceInfo = scanner.nextLine();
            if (!chanceInfo.isEmpty()) {
                ArrayList<String> cardData = generateCardData(chanceInfo);
                if (getCardType(cardData).equals(CARD_CHANCE) && cardType.equals(CARD_CHANCE)) {
                    addCard(cards, cardData);
                } else if (getCardType(cardData).equals(CARD_COMMUNITYCHEST) && cardType.equals(CARD_COMMUNITYCHEST)) {
                    addCard(cards, cardData);
                }
            }
        }
		scanner.close();

		return cards;
	}
	
	private static void addCard(ArrayList<Card> cards, ArrayList<String> cardData) {
		Card card = new Card();
		card.text = getCardText(cardData);
		card.command = getCardCommand(cardData);
		cards.add(card);
	}

	private static ArrayList<String> generateCardData(String cardInfo) {
		StringTokenizer tokenizer = new StringTokenizer(cardInfo, ",");
		
		ArrayList<String> cardData = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()) {
			cardData.add(tokenizer.nextToken());
		}
		return cardData;
	}

	private static String getCardType(ArrayList<String> cardData) {
		return getValueAt(cardData, 0);
	}
	
	private static String getCardText(ArrayList<String> cardData) {
		return getValueAt(cardData, 1);
	}

	private static Command getCardCommand(ArrayList<String> cardData) {
		String commandTitle = getValueAt(cardData, 2);
		if (commandTitle.equals(COMMAND_GETPRIZE)) {
			return new GetPrizeCommand(Integer.parseInt(getValueAt(cardData, 3)));
		} else if (commandTitle.equals(COMMAND_MOVELAND)) {
			return new MoveLandCommand(getValueAt(cardData, 3));
		} else if (commandTitle.equals(COMMAND_PAYFEE)) {
			return new PayFeeCommand(Integer.parseInt(getValueAt(cardData, 3)));
		} else if (commandTitle.equals(COMMAND_MOVEBACKWARD)) {
			return new MoveBackwardCommand(Integer.parseInt(getValueAt(cardData, 3)));
		} else if (commandTitle.equals(COMMAND_DRAWCHANCE)) {
			return new DrawChanceCommand();
		} else {
			throw new RuntimeException("Unknown command type!");
		}
	}
	
	private static String getValueAt(ArrayList<String> cardData, int index) {
		return cardData.get(index);
	}
	
}
