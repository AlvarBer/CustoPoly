package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Token;

public abstract class MoveTokenCommand implements Command {

	@Override
	public void execute(Controller controller) {
		int landIndex = getLandIndex(controller);
		Land land = controller.getGame().getBoard().getLands().get(landIndex);

		Token token = controller.getGame().getCurrentPlayer().getToken();
		checkPassedStart(token.getLandIndex(), landIndex, controller);
		token.setLocation(landIndex);
		controller.update();
		
		Command command = land.getAssignment();
		command.execute(controller);
	}

	private void checkPassedStart(int oldIndex, int newIndex, Controller controller) {
		if (newIndex < oldIndex && isForward()) {
			GetPaidCommand command = new GetPaidCommand();
			command.execute(controller);
		}
	}

	public abstract boolean isForward();

	public abstract int getLandIndex(Controller controller);

}
