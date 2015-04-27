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

	//Methods


	@Override
	public void buyAsset() {new AskBuyCommand();}

	@Override
	public void rollDice() {new RollDiceCommand();}


    @Override
    public ArrayList<PropertyLand> getAssetsOwnedByPlayer() {
        return currentPlayer.getProperties();
	}


    @Override
    public Player getOwner(PropertyLand property) {
        for (Player player: players) {
			if(player.getProperties().contains(property))
				return player;
		}
		return null;
	}


    @Override
    public Bank getBank() {
        return bank;
	}


    @Override
    public Board getBoard() {
        return board;
	}


    @Override
    public ArrayList<Player> getPlayers() {
        return players;
	}


    @Override
    public boolean hasWinner() {
        if(players.size() == 1) return true;
		else return false;
	}


    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
	}


    @Override
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
	}

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

    @Override
    public GameTheme getTheme() {
        return theme;
	}
}
