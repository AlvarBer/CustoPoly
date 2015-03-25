package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class GetPrizeCommand implements Command {
	
	private int prize;
	
	public GetPrizeCommand(int prize) {
		this.prize = prize;
	}

	@Override
	public void execute(Controller controller) {
		Player player = controller.getGame().getCurrentPlayer();
		player.increaseBalance(prize);
	}

}
