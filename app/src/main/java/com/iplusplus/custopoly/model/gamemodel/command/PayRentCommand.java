package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

public class PayRentCommand implements Command {

	@Override
	public void execute(Controller controller) {
		Game game = controller.getGame();
		PropertyLand property = (PropertyLand) getCurrentLand(game);
		int payment = property.acceptCalculator(new RentCalculator(game));
		makeTransaction(game, payment);
	}

	protected Land getCurrentLand(Game game) {
		return game.getBoard().getLands().get(game.getCurrentPlayer().getToken().getLandIndex());
	}

	private void makeTransaction(Game game, int payment) {
		Player source = game.getCurrentPlayer();
		Player target = game.getOwner((PropertyLand) getCurrentLand(game));
		if(!source.equals(target)) {
			source.decreaseBalance(payment);
			target.increaseBalance(payment);
            //TODO: Notify the user that a transaction has been made.
			//JOptionPane.showMessageDialog(null, source.getName() + " paid " + payment + "K to " + target.getName());
		}
	}



}
