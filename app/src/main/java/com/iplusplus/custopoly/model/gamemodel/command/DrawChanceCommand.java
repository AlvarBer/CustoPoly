package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Card;

import java.util.ArrayList;

public class DrawChanceCommand extends DrawCardCommand {

    @Override
    protected ArrayList<Card> getCards(GameFacade game) {
        return game.getBank().getChanceCards();
    }
}
