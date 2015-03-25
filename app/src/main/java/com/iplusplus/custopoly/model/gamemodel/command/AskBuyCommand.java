package com.iplusplus.custopoly.model.gamemodel.command;


import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

public class AskBuyCommand implements Command {

    /** TODO
     * Display a dialog to ask whether the current player wats t opurchase a piece of land.
     * @param controller
     */
	@Override
	public void execute(Controller controller) {
		Game game = controller.getGame();
		PropertyLand land = (PropertyLand) game.getBoard().getLands().get(game.getCurrentPlayer().getToken().getLandIndex());

        /*
		if(getReply(controller, land) == JOptionPane.YES_OPTION) {
			makePurchase(controller, land);
		} else {
			display("Player " + game.getCurrentPlayer().getName() + " did not want to purchase " + land.getName(), controller);
		}*/
		
	}

    /** TODO
     * Receive reply from the dialog
     * @param controller
     * @param land
     * @return
     */
	private int getReply(Controller controller, PropertyLand land) {
        /*
		String message = "Do you want to buy " + land.getName() + " for " + land.getPrice() + "K ?";
		String title = controller.getGame().getCurrentPlayer().getName();
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		return reply;*/
        return 0;
	}

    /**
     * Current player purchases a land.
     * @param controller
     * @param land
     */
	private void makePurchase(Controller controller, PropertyLand land) {
		Player player = controller.getGame().getCurrentPlayer();
		if(player.getBalance() > land.getPrice()) {
			player.decreaseBalance(land.getPrice());
			player.addProperty(land);
			land.setAssignment(new PayRentCommand());
			display(player.getName() + " owned " + land.getName(), controller);
		} else {
			display("Purchase of " + land.getName() + " denied", controller);
		}
	}

    /** TODO
     * Display a dialog with some information.
     * @param info
     * @param controller
     */
	private void display(String info, Controller controller) {
		//JOptionPane.showMessageDialog(null, info);
	}

}
