package com.iplusplus.custopoly.model.gamemodel.Observer;

import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;

/**
 * Created by Jorge on 29/04/2015.
 */
public interface GameObserver{

    //GameObserver Events
    void onAttached(GameTheme theme, Board board, Player currentPlayer, ArrayList<Player> playersList);

    //Game Events
    void onGameBegin(GameTheme theme, Board board, Player currentPlayer);
    void onGameEnd(Board board, ArrayList<Player> playersList);
    void onGameReset(GameTheme theme, Board board, Player currentPlayer);

    //Turn events
    void onTurnBegin(Board board, Player player);
    void onTurnEnd(Board board, Player player);

    //Action events
    void onViewProperties(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<PropertyLand> mortgagedProperties);

    void onMortgage(Player currentPlayer);
    void onRollDiceBegin(Board board, Player currentPlayer);
    void onRollDiceEnd(Board board, Player currentPlayer);

    void onCard(String text);
    void onPayFee(String message);
    void onPurchasableCard(final String playerName, final PropertyLand land);
    void onBoughtCard(Player name, PropertyLand land);
    void onBuyError(Player name, PropertyLand land);
}
