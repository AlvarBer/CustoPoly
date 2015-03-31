package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class EndTurnCommand implements Command {

    /**
     * TODO
     * This method passes the turn to the next player and tells the controller to update itself.
     *
     * @param game
     * @param context
     */
    @Override
    public void execute(Game game, Context context) {
        int newPlayerIndex = (game.getPlayers().indexOf(game.getCurrentPlayer()) + 1) % game.getPlayers().size();
        game.setCurrentPlayer(game.getPlayers().get(newPlayerIndex));

        //controller.update();
    }
}
