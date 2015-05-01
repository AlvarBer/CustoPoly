package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

/**
 * Created by fran on 02/05/2015.
 */
public class BuyCommand implements Command {

    /**
     * Current player purchases a land.
     *
     * @param game
     */
    @Override
    public void execute(Game game) {
        PropertyLand land = (PropertyLand) game.getBoard().getLands().get(game.getCurrentPlayer().getLandIndex());
        Player player = game.getCurrentPlayer();
        if (player.getBalance() > land.getPrice()) {
            player.decreaseBalance(land.getPrice());
            player.addProperty(land);
            land.setAssignment(new PayRentCommand());
            for (GameObserver o : game.getObserversList())
                o.onBoughtCard(game.getCurrentPlayer(), land);
        }
        else {
            for (GameObserver o : game.getObserversList())
                o.onBuyError(game.getCurrentPlayer(), land);
        }

    }

}
