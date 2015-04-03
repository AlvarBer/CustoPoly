package com.iplusplus.custopoly.model.gamemodel.command;


import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Token;

public abstract class MoveTokenCommand implements Command {

    @Override
    public void execute(Game game) {
        int landIndex = getLandIndex(game);
        Land land = game.getBoard().getLands().get(landIndex);

        Token token = game.getCurrentPlayer().getToken();
        checkPassedStart(token.getLandIndex(), landIndex, game);
        token.setLocation(landIndex);
        //TODO: We need to update the controller
        //controller.update();

        Command command = land.getAssignment();
        command.execute(game);
    }

    private void checkPassedStart(int oldIndex, int newIndex, Game game) {
        if (newIndex < oldIndex && isForward()) {
            GetPaidCommand command = new GetPaidCommand();
            command.execute(game);
        }
    }

    public abstract boolean isForward();

    public abstract int getLandIndex(Game game);


}
