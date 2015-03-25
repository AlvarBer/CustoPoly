package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.controller.Controller;

public class GoJailCommand implements Command {

	@Override
	public void execute(Controller controller) {
		(new MoveBackwardCommand(20)).execute(controller);
		//TODO

	}

}
