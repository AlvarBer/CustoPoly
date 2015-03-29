package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

//TODO: Esta clase implementa onClicklistener para poder mantener flags de selecciones.
public class AskBuyCommand implements Command, DialogInterface.OnClickListener {

    private boolean buyOrNot;

    /**
     * Display a dialog to ask whether the current player wats t opurchase a piece of land.
     * @param game
     * @param context
     */
	@Override
	public void execute(Game game, Context context) {
		PropertyLand land = (PropertyLand) game.getBoard().getLands().get(game.getCurrentPlayer().getToken().getLandIndex());

        this.askPurchase(game, context, land);
		if(this.buyOrNot) {
			makePurchase(game, context, land);
		} else {
			display("Player " + game.getCurrentPlayer().getName() + " did not want to purchase " + land.getName(), game, context);
		}
		
	}

    /**
     * create and display a dialog to see if the player wants to buy the land.
     * @param game
     * @param context
     * @param land
     */
    private void askPurchase(Game game, Context context, PropertyLand land) {

        String message = "Do you want to buy " + land.getName() + " for " + land.getPrice() + "K ?";
        String title = game.getCurrentPlayer().getName();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton("Yes", this).setNegativeButton("No", this);
        builder.show();
    }

    /**
     * Current player purchases a land.
     * @param game
     * @param context
     * @param land
     */
	private void makePurchase(Game game, Context context, PropertyLand land) {
		Player player = game.getCurrentPlayer();
		if(player.getBalance() > land.getPrice()) {
			player.decreaseBalance(land.getPrice());
			player.addProperty(land);
			land.setAssignment(new PayRentCommand());
			display(player.getName() + " owned " + land.getName(), game, context);
		} else {
			display("Purchase of " + land.getName() + " denied", game,  context);
		}
	}

    /**
     * Display a dialog to notify he purchase.
     * @param info
     * @param game
     * @param context
     */
	private void display(String info, Game game, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(info).show();
	}

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                this.buyOrNot = true;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                this.buyOrNot = false;
                break;
        }
    }
}
