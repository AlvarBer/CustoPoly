package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.element.DicePair;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class RollDiceCommand implements Command {

    @Override
    public void execute(Game game) {
        DicePair.roll();
        movePlayer(game);
    }

    private void movePlayer(Game game) {
        int stepForward = DicePair.getDiceValue();
        MoveForwardCommand moveCommand = new MoveForwardCommand(stepForward);
        moveCommand.execute(game);
    }

}
