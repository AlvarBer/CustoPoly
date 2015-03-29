package com.iplusplus.custopoly.model.gamemodel.command;


import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class GoJailCommand implements Command {

    @Override
    public void execute(Game game, Context context) {
        (new MoveBackwardCommand(20)).execute(game, context);
    }
}
