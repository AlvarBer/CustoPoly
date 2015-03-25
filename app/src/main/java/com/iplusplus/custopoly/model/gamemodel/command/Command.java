package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;

public interface Command {
		
	public void execute(Controller controller);

}
