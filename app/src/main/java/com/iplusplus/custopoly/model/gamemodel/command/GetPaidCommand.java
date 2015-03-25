package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class GetPaidCommand implements Command {

	@Override
	public void execute(Controller controller) {
		Player player = controller.getGame().getCurrentPlayer();
		player.increaseBalance(controller.getGame().PAYMENT);
	}

}
