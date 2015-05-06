package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class RollDiceCommand implements Command {

    private int diceResult;

    public RollDiceCommand(int diceResult) {
        this.diceResult = diceResult;
    }

    @Override
    public void execute(Game game) {
        movePlayer(game);
    }

    private void movePlayer(Game game) {
        MoveForwardCommand moveCommand = new MoveForwardCommand(diceResult);
        moveCommand.execute(game);
    }


}
