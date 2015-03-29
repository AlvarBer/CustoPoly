package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.element.Card;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

import java.util.ArrayList;

public class DrawCommunityCommand extends DrawCardCommand {


    @Override
    protected ArrayList<Card> getCards(Game game) {
        return game.getBank().getCommunityCards();
    }
}
