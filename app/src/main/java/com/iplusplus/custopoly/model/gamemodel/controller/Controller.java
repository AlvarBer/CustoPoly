/*package com.iplusplus.custopoly.model.gamemodel.controller;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.command.Command;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.File;
import java.util.ArrayList;

//All this will have to be integrated within an activity.
public class Controller {

	public static void main(String[] args) {
		Controller gameController = new Controller();
		gameController.start();
	}

	private Game game;
	//private GameScreen gameFrame;

	private String boardFilePath = "assets/gameBoard.txt";
	private String cardFilePath = "assets/cards.txt";

	public Controller() {
		ArrayList<Player> players = getPlayers();
		initGame(players);
		initCards();
	}


	private ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		int playerAmount = askNumberOfPlayers();
		for (int i = 0; i < playerAmount; i++) {
            //TODO: add a new player to the game.
			//players.add(new Player(i, JOptionPane.showInputDialog(String.format("#%d Player Name : ", i + 1))));
		}
		return players;
	}

	private int askNumberOfPlayers() {
		int numPlayer = 2;
		try {
			do {
                //TODO: Make numPlayer get the number of players.
				//numPlayer = Integer.parseInt(JOptionPane.showInputDialog("Enter Player Amount"));
			} while (2 > numPlayer || numPlayer > 6);
		} catch (Exception e) {
			System.exit(0);
		} finally{
			return numPlayer;
		}
		
	}

	private void initCards() {
		Bank bank = game.getBank();
		bank.chanceCards = CardFactory.readChanceCards(new File(cardFilePath));
		bank.communityCards = CardFactory.readCommunityCards(new File(cardFilePath));
	}

    //TODO: create the frame and set it to visible.
	private void start() {
		//gameFrame = new GameScreen(this);
		//gameFrame.setVisible(true);
	}

    //TODO: reset the main frame.
	public void update() {
        //gameFrame.reset();
	}

	public void execute(Game game, Context context) {
		//command.execute(this);
	}

	public Game getGame() {
		return game;
	}

}*/
