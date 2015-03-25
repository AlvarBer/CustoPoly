package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class EndTurnCommand implements Command {

	@Override
	public void execute(Controller controller) {
		Game game = controller.getGame();
		int newPlayerIndex = (game.getPlayers().indexOf(game.getCurrentPlayer()) + 1) % game.getPlayers().size();
		game.setCurrentPlayer(game.getPlayers().get(newPlayerIndex));

		controller.update();
	}

}
