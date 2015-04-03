package com.iplusplus.custopoly.model.gamemodel.element;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.command.AskBuyCommand;
import com.iplusplus.custopoly.model.gamemodel.command.RollDiceCommand;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.GameTheme;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Game implements GameFacade, Serializable{

	//Attributes
	private Bank bank;
	private Board board;
	private ArrayList<Player> players;
	
	private Player currentPlayer;
	
	public final int PAYMENT = 1500; 
	public static final int INITIAL_PAYMENT = 15000;

	//Constructor
	public Game(ArrayList<Player> players, Board board) {
		initBank(board.getLands());
		this.board = board;
		this.players = players;
		this.currentPlayer = players.get(0);
	}

	//Methods


	@Override
	public void buyAsset() {new AskBuyCommand();}

	@Override
	public void rollDice() {new RollDiceCommand();}


	public ArrayList<PropertyLand> getAssetsOwnedByPlayer() {
		return currentPlayer.getProperties();
	}


	public Player getOwner(PropertyLand property){
		for (Player player: players) {
			if(player.getProperties().contains(property))
				return player;
		}
		return null;
	}


	public Bank getBank() {
		return bank;
	}


	public Board getBoard() {
		return board;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public boolean hasWinner() {
		if(players.size() == 1) return true;
		else return false;
	}


	public Player getCurrentPlayer() {
		return currentPlayer;
	}


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
		return BoardFactory.readBoard(new File(theme.getBackgroundPath()));
	}

}
