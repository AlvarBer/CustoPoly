package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class MoveBackwardCommand extends MoveTokenCommand {
	
	private int stepBackward;
	
	public MoveBackwardCommand(int stepBackward) {
		this.stepBackward = stepBackward;
	}

	@Override
	public int getLandIndex(Controller controller) {
		Game game = controller.getGame();
		int currentIndex = game.getCurrentPlayer().getToken().getLandIndex();
		int movedIndex = ((currentIndex - stepBackward) + game.getBoard().getLands().size()) % game.getBoard().getSize();
		return movedIndex;
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
