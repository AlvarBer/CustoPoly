package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Facade for the GameModel subsystem
 */
public interface GameFacade {

    /**
     */

    /**
     * It instantiates all things necessary to begin a game.
     * The number of players is given by the size of the list.
     *
     * @param players
     * A list containing the players that will be involved in the game.
     */
    //public void createGame(Collection<Player> players);

    /**
     * Method that allows a player to purchase an asset.
     *
     * Cell in which the asset is located.
     */
    public void buyAsset();

    /**
     * Roll the dice and move the current player
     *
     */
    public void rollDice();
    /**
     * @return
     * Returns the asset list with the assets owned by the current player.
     */

}