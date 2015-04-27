package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;

public interface GameFacade {
    void buyAsset();

    void rollDice();

    ArrayList<PropertyLand> getAssetsOwnedByPlayer();

    Player getOwner(PropertyLand property);

    Bank getBank();

    Board getBoard();

    ArrayList<Player> getPlayers();

    boolean hasWinner();

    Player getCurrentPlayer();

    void setCurrentPlayer(Player player);

    GameTheme getTheme();
}
