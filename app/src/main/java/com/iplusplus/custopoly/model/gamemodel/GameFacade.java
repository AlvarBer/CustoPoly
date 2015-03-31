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
public class GameFacade {
    private List<Player> players;
    private Bank bank;
    private Board board;
    private static GameFacade INSTANCE;
    /**
     * Factory Method for Singleton object.
     *
     * @return
     * Returns the single instance of GameFacade that there should be.
     * Ideally, it should initialize INSTANCE if it's not initialized,
     * and then return it.
     */
    public static GameFacade getInstance() {
        return INSTANCE;
    }
    /**
     * It instantiates all things necessary to begin a game.
     * The number of players is given by the size of the list.
     *
     * @param names
     * A list containing the names of the players.
     */
    public static void createGame(Collection<String> names) {}
    /**
     * Method that allows a player to purchase a property.
     *
     * @param land
     * Cell in which the asset is located.
     */
    public static void buyAsset(Land land){}
    /**
     * Moves the player.
     *
     * @param playerName
     * The name of the player that has to move.
     */
    public static void move(String playerName) {}
    /**
     * @param playerName
     * The name of the player of which we want the assets.
     * @return
     * Returns the asset list with the assets owned by.
     */
    public static ArrayList<PropertyLand> getAssetsOwnedByPlayer(String playerName) {
        return null;
    }
}