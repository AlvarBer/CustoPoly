package com.iplusplus.custopoly.model.gamemodel.element;

import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.exceptions.PlayerNotFoundException;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.command.BuyCommand;
import com.iplusplus.custopoly.model.gamemodel.command.EndTurnCommand;
import com.iplusplus.custopoly.model.gamemodel.command.RollDiceCommand;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements GameFacade, Serializable {

    //Attributes
    private ArrayList<GameObserver> observersList;

    private Bank bank;
    private Board board;
    private ArrayList<Player> playersList;

    private Player currentPlayer;
    private GameTheme theme;

    public final int PAYMENT = 1500;
    public static final int INITIAL_PAYMENT = 15000;

    //Constructor
    public Game(ArrayList<Player> playersList, Board board, GameTheme theme) {
        observersList = new ArrayList<GameObserver>();
        initBank(board.getLands());
        this.board = board;
        this.playersList = playersList;
        this.currentPlayer = playersList.get(0);
        this.theme = theme;
    }

    // //
    //GameObservable Methods (From GameFactory)
    // //

    @Override
    public void addObserver(GameObserver o) {
        observersList.add(o);
        o.onAttached(this.theme, this.board, this.currentPlayer, this.playersList);
    }

    @Override
    public void deleteObserver(GameObserver o) {
        observersList.remove(o);
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

    //Auxiliary methods for the getters
    private Player findPlayerById(int playerId) {
        Player player = null;
        for (Player p : this.playersList) {
            if (p.getPlayerID() == playerId) {
                player = p;
            }
        }
        if (player == null) throw new PlayerNotFoundException();
        return player;
    }

    private Player findPlayerByName(String playerName) {
        Player player = null;
        for (Player p : this.playersList) {
            if (p.getName().equals(playerName)) {
                player = p;
            }
        }
        if (player == null) throw new PlayerNotFoundException();
        return player;
    }

    public Bank getBank() {
        return this.bank;
    }

    // //
    // Methods implemented from GameFacade
    // //

    @Override
    public void buyAsset() {
        new BuyCommand().execute(this);
    }

    @Override
    public void viewProperties() {
        for (GameObserver o: observersList)
            o.onViewProperties(this.currentPlayer.getProperties());
    }

    @Override
    public void passTurn() {
        new EndTurnCommand().execute(this);
    }

    @Override
    public void rollDice() {
        new RollDiceCommand().execute(this);
        for (GameObserver o: observersList)
            o.onRollDice (this.board, this.currentPlayer);

    }


    public ArrayList<String> getAssetsOwnedByCurrentPlayer() {
        ArrayList<String> names = new ArrayList<String>();
        for (PropertyLand pr : this.currentPlayer.getProperties()) {
            names.add(pr.getName());
        }
        return names;
    }


    public ArrayList<String> getAssetNamesOwnedByPlayer(int playerId) {
        Player player = findPlayerById(playerId);
        ArrayList<String> names = new ArrayList<String>();
        for (PropertyLand pr : player.getProperties()) {
            names.add(pr.getName());
        }
        return names;
    }


    public ArrayList<String> getAssetNamesOwnedByPlayer(String playerName) {
        Player player = findPlayerByName(playerName);
        ArrayList<String> names = new ArrayList<String>();
        for (PropertyLand pr : player.getProperties()) {
            names.add(pr.getName());
        }
        return names;
    }


    public String getOwnerName(String propertyName) {
        String name = null;
        for (Player p : this.playersList) {
            for (PropertyLand pr : p.getProperties()) {
                if (pr.getName().equals(propertyName))
                    name = p.getName();
            }
        }
        if (name == null) throw new PlayerNotFoundException();
        return name;
    }

    public int getOwnerId(String propertyName) {
        int id = -1;
        for (Player p : this.playersList) {
            for (PropertyLand pr : p.getProperties()) {
                if (pr.getName().equals(propertyName))
                    id = p.getPlayerID();
            }
        }
        if (id == -1) throw new PlayerNotFoundException();
        return id;
    }



    public int getBoardSize() {
        return this.board.getSize();
    }


    public ArrayList<String> getPlayerNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Player p : this.playersList) {
            names.add(p.getName());
        }
        return names;
    }


    public int getPlayerIdByName(String playerName) {
        return findPlayerByName(playerName).getPlayerID();
    }


    public String getPlayerNameById(int playerID) {
        return findPlayerById(playerID).getName();
    }


    public boolean isEnded() {
        //TODO: Unfinished
        return false;
    }


    public String getWinnerName() {
        //TODO: Unfinished
        return null;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }


    public ArrayList<Player> getPlayers() {
        return playersList;
    }


    public String getCurrentPlayerSkinResPath() {
        return this.currentPlayer.getSkin().getImageResourceName();
    }


    public String getPlayerSkinResPathById(int playerId) {
        return findPlayerById(playerId).getSkin().getImageResourceName();
    }


    public String getPlayerSkinResPathByName(String playerName) {
        return findPlayerByName(playerName).getSkin().getImageResourceName();
    }


    public String getCurrentPlayerName() {
        return this.currentPlayer.getName();
    }


    public int getCurrentPlayerId() {
        return this.currentPlayer.getPlayerID();
    }


    public int getPlayerBalanceById(int playerId) {
        return findPlayerById(playerId).getBalance();
    }


    public int getPlayerBalanceByName(String playerName) {
        return findPlayerByName(playerName).getBalance();
    }


    public int getCurrentPlayerBalance() {
        return this.currentPlayer.getBalance();
    }


    public ArrayList<String> getCurrentPlayerCardList() {

        return null;
    }


    public ArrayList<String> getPlayerCardListById(int playerId) {
        return null;
    }


    public ArrayList<String> getPlayerCardListByName(String playerName) {
        return null;
    }


    public GameTheme getCurrentTheme() {
        return theme;
    }


    public String getCurrentThemeBackgroundResPath() {
        return this.theme.getBackgroundPathResource();
    }


    public String getCurrentThemeCCBoxCardResPath() {
        return this.theme.getCommunityBoxCardPathResource();
    }

    public String getCurrentThemeFortuneCardResPath() {
        return this.theme.getFortuneCardPathResource();
    }

    public ArrayList<GameObserver> getObserversList() {
        return observersList;
    }

    public void setObserversList(ArrayList<GameObserver> observersList) {
        this.observersList = observersList;
    }


    public ArrayList<PropertyLand> getAssetsOwnedByPlayer() {
        return currentPlayer.getProperties();
    }

    public Player getOwner(PropertyLand property) {
        for (Player player : playersList) {
            if (player.getProperties().contains(property))
                return player;
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }


    public boolean hasWinner() {
        if (playersList.size() == 1) return true;
        else return false;
    }



}
