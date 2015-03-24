package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.gamemodel.cellelements.Cell;
import com.iplusplus.custopoly.model.gamemodel.cellelements.PropertyLand;

import java.util.Collection;

/**
 * Facade for the GameModel subsystem
 */
public class Game {

    private static Game INSTANCE;

    /**
     * Factory Method for Singleton object.
     *
     * @return
     *          Returns the single instance of Game that there should be.
     *          Ideally, it should initialize INSTANCE if it's not initialized,
     *          and then return it.
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * It instantiates all things necessary to begin a game.
     * The number of players is given by the size of the list.
     *
     * @param names
     *          A list containing the names of the players.
     */
    public static void createGame(Collection<String> names) {}

    /**
     * Method that allows a player to purchase a property.
     *
     * @param cell
     *          Cell in which the asset is located.
     */
    public static void buyAsset(Cell cell){}

    /**
     * Moves the player.
     *
     * @param playerName
     *          The name of the player that has to move.
     */
    public static void move(String playerName) {}

    /**
     * @param playerName
     *          The name of the player of which we want the assets.
     * @return
     *          Returns the asset list with the assets owned by.
     */
    public static PropertyLand getAssetsOwnedByPlayer(String playerName) {
        return null;
    }

}
