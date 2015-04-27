package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.command.AskBuyCommand;
import com.iplusplus.custopoly.model.gamemodel.command.RollDiceCommand;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.GameTheme;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Game implements GameFacade, Serializable {

    //Attributes
    private Bank bank;
    private Board board;
    private ArrayList<Player> players;

    private Player currentPlayer;
    private GameTheme theme;

    public final int PAYMENT = 1500;
    public static final int INITIAL_PAYMENT = 15000;

    //Constructor
    public Game(ArrayList<Player> players, Board board, GameTheme theme) {
        initBank(board.getLands());
        this.board = board;
        this.players = players;
        this.currentPlayer = players.get(0);
        this.theme = theme;
    }

    // //
    //Private Methods
    // //

    private void initBank(ArrayList<Land> lands) {
        ArrayList<PropertyLand> propertyLands = new ArrayList<PropertyLand>();
        for (Land land : lands) {
            if (land instanceof PropertyLand) {
                propertyLands.add((PropertyLand) land);
            }
        }
        bank = new Bank(propertyLands);
    }

    private Board initBoard(GameTheme theme) {
        return BoardFactory.readBoard(new File(theme.getBoardDataPath()));
    }

    // //
    // Methods implemented from interface
    // //

    @Override
    public void moveCurrentPlayer() {
        //TODO: Fill in this method. Similar to executemove from the TP project
    }

    @Override
    public void passTurn() {
        this.currentPlayer = this.players.get(this.players.indexOf(this.currentPlayer) % this.players.size());
    }

    @Override
    public ArrayList<String> getAssetsOwnedByCurrentPlayer() {
        return null;
    }

    @Override
    public String getOwnerName(String propertyName) {
        return null;
    }

    @Override
    public int getOwnerId(String propertyName) {
        return 0;
    }

    @Override
    public Bank getBank() {
        return null;
    }

    @Override
    public int getBoardSize() {
        return 0;
    }

    @Override
    public ArrayList<String> getPlayerNames() {
        return null;
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    @Override
    public String getWinnerName() {
        return null;
    }

    @Override
    public String getCurrentPlayerName() {
        return null;
    }

    @Override
    public String getCurrentThemeBackgroundResPath() {
        return null;
    }

    @Override
    public String getCurrentThemeCardsResPath() {
        return null;
    }


    // //
    //  Temporary methods ONLY FOR INTERNAL CLASSES
    // //
    public void buyAsset() {
        new AskBuyCommand();
    }


    public void rollDice() {
        new RollDiceCommand();
    }


    public ArrayList<PropertyLand> getAssetsOwnedByPlayer() {
        return currentPlayer.getProperties();
    }

    public Player getOwner(PropertyLand property) {
        for (Player player : players) {
            if (player.getProperties().contains(property))
                return player;
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }


    public boolean hasWinner() {
        if (players.size() == 1) return true;
        else return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public GameTheme getTheme() {
        return theme;
    }
}
