package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.DicePair;

public class RollDiceCommand implements Command {

	@Override
	public void execute(Controller controller) {
		DicePair.roll();
		controller.update();
		movePlayer(controller);
		controller.update();
	}
	
	private void movePlayer(Controller controller) {
		int stepForward = DicePair.getDiceValue();
		MoveForwardCommand moveCommand = new MoveForwardCommand(stepForward);
		moveCommand.execute(controller);
	}

}
