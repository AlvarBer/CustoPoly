package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Card;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

import java.util.ArrayList;

public abstract class DrawCardCommand implements Command {

    @Override
    public void execute(Game game) {
        ArrayList<Card> cards = getCards(game);
        Card drawnCard = cards.remove(0);

        for (GameObserver o: game.getObserversList())
            o.onCard(drawnCard.text);
        drawnCard.command.execute(game);

        cards.add(drawnCard);
    }


    protected abstract ArrayList<Card> getCards(Game game);

}
