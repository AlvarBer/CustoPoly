package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Card;

import java.util.ArrayList;

public abstract class DrawCardCommand implements Command {
	
	@Override
	public void execute(Controller controller) {
		ArrayList<Card> cards = getCards(controller);
		Card drawnCard = cards.remove(0);
		
		displayText(drawnCard.text);
		drawnCard.command.execute(controller);
		
		cards.add(drawnCard);
	}

    /** TODO
     * Displays a dialog with some text.
     * @param text
     */
	private void displayText(String text) {
		//JOptionPane.showMessageDialog(null, text);
	}
	
	protected abstract ArrayList<Card> getCards(Controller controller);
	
}
