package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Land;

public class MoveLandCommand extends MoveTokenCommand {

    private String landName;

    public MoveLandCommand(String landName) {
        this.landName = landName;
    }

    @Override
    public int getLandIndex(Game game) {
        Board board = game.getBoard();
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
