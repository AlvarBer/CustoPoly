package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.DicePair;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class RollDiceCommand implements Command {

    @Override
    public void execute(Game game, Context context) {
        DicePair.roll();
        //TODO: Tell the controller to update.
        //controller.update();
        movePlayer(game, context);
        //TODO: Tell the controller to update.
        //controller.update();
    }

    private void movePlayer(Game game, Context context) {
        int stepForward = DicePair.getDiceValue();
        MoveForwardCommand moveCommand = new MoveForwardCommand(stepForward);
        moveCommand.execute(game, context);
    }

}
