package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

public class PurchasableCommand implements Command {


    /**
     * Dispatch an event to make a property purchasable
     *
     * @param game
     */
    @Override
    public void execute(Game game) {
        PropertyLand land = (PropertyLand) game.getBoard().getLands().
                get(game.getCurrentPlayer().getLandIndex());
        for (GameObserver o: game.getObserversList())
            o.onPurchasableCard(game.getCurrentPlayer().getName(),land);
    }
}
