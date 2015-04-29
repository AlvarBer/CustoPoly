package com.iplusplus.custopoly.model.gamemodel.Observer;

import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

import java.util.ArrayList;

/**
 * Created by Jorge on 29/04/2015.
 */
public interface GameObserver {

    //GameObserver Events
    public void onAttached(GameTheme theme, Board board, Player currentPlayer, ArrayList<Player> playersList);

    //Game Events
    public void onGameBegin(GameTheme theme, Board board, Player currentPlayer);
    public void onGameEnd(Board board, ArrayList<Player> playersList);
    public void onGameReset(GameTheme theme, Board board, Player currentPlayer);

    //Turn events
    public void onTurnBegin(Board board, Player player);
    public void onTurnEnd(Board board, Player player);
}
