package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public class EndTurnCommand implements Command {

    /**
     * This method passes the turn to the next player and tells the controller to update itself.
     *
     * @param game
     */
    @Override
    public void execute(Game game) {
        int index = game.getPlayers().indexOf(game.getCurrentPlayer());
        if (index == game.getPlayers().size()-1)
            index = 0;
        else
            index++;
        game.setCurrentPlayer(game.getPlayers().get(index));
        for (GameObserver o: game.getObserversList()) {
            o.onTurnEnd(game.getBoard(), game.getCurrentPlayer());
            o.onTurnBegin(game.getBoard(), game.getCurrentPlayer());
        }
    }
}
