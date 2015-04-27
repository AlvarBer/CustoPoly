package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class MoveBackwardCommand extends MoveTokenCommand {

    private int stepBackward;

    public MoveBackwardCommand(int stepBackward) {
        this.stepBackward = stepBackward;
    }

    @Override
    public int getLandIndex(Game game) {
        int currentIndex = game.getCurrentPlayer().getLandIndex();
        int movedIndex = ((currentIndex - stepBackward) + game.getBoard().getLands().size()) % game.getBoard().getSize();
        return movedIndex;
    }

    @Override
    public boolean isForward() {
        return false;
    }


}
