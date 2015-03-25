package com.iplusplus.custopoly.model.gamemodel.command;

import com.iplusplus.custopoly.model.gamemodel.controller.Controller;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class PayFeeCommand implements Command {
	
	private int fee;
	
	public PayFeeCommand(int fee) {
		this.fee = fee;
	}


	@Override
	public void execute(Controller controller) {
		Player player = controller.getGame().getCurrentPlayer();
		player.decreaseBalance(fee);
        //TODO: Change to an Android dialog, it notifies that the fee is paid.
		//JOptionPane.showMessageDialog(null, String.format("%s paid : %dK", player.getName(), fee));
	}

}
