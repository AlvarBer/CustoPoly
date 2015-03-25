package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Land;

public class MoveLandCommand extends MoveTokenCommand {
	
	private String landName;
	
	public MoveLandCommand(String landName) {
		this.landName = landName;
	}

	@Override
	public int getLandIndex(Controller controller) {
		Board board = controller.getGame().getBoard();
		for (Land land : board.getLands()) {
			if (land.getName().equals(landName)) {
				return board.getLands().indexOf(land);
			}
		}
		throw new RuntimeException("Land Name NOT found.");
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
