package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class MoveForwardCommand extends MoveTokenCommand {
	
	private int stepForward;
	
	public MoveForwardCommand(int stepForward) {
		this.stepForward = stepForward;
	}

    @Override
    public int getLandIndex(Game game) {
        int currentIndex = game.getCurrentPlayer().getToken().getLandIndex();
        int movedIndex = (currentIndex + stepForward) % game.getBoard().getSize();
        return movedIndex;
    }

	@Override
	public boolean isForward() {
		return true;
	}
}
