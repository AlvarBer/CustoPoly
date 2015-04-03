package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

//TODO: Esta clase implementa onClicklistener para poder mantener flags de selecciones.
public class AskBuyCommand implements Command, DialogInterface.OnClickListener {

    private boolean buyOrNot;

    /**
     * Display a dialog to ask whether the current player w.nats to purchase a piece of land.
     *
     * @param game
     */
    @Override
    public void execute(Game game) {
        Context context = Custopoly.getAppContext();
        PropertyLand land = (PropertyLand) game.getBoard().getLands().
                                            get(game.getCurrentPlayer().getToken().getLandIndex());

        this.askPurchase(game, land);
        if (this.buyOrNot) {
            makePurchase(game, context, land);
        } else {
            String s = context.getResources().getText(R.string.ingame_notWantToBuy).toString();
            String fs = String.format(s, game.getCurrentPlayer().getName(), land.getName());
            display(fs, game);
        }

    }

    /**
     * create and display a dialog to see if the player wants to buy the land.
     *
     * @param game
     * @param land
     */
    private void askPurchase(Game game, PropertyLand land) {
        Context context = Custopoly.getAppContext();

        String message = context.getText(R.string.ingame_askWantToBuy).toString();
        String formatMessage = String.format(message, land.getName(), land.getPrice());
        String title = game.getCurrentPlayer().getName();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(formatMessage).setPositiveButton(context.getText(R.string.ingame_buyyesbutton), this).
                setNegativeButton(context.getText(R.string.ingame_buynobutton), this);
        builder.show();
    }

    /**
     * Current player purchases a land.
     *
     * @param game
     * @param context
     * @param land
     */
    private void makePurchase(Game game, Context context, PropertyLand land) {
        Player player = game.getCurrentPlayer();
        if (player.getBalance() > land.getPrice()) {
            player.decreaseBalance(land.getPrice());
            player.addProperty(land);
            land.setAssignment(new PayRentCommand());
            String message = String.format(context.getText(R.string.ingame_buySuccess).toString(),
                                            player.getName(), land.getName());
            display(message, game);
        } else {
            String message = String.format(context.getText(R.string.ingame_buyFailure).toString(), land.getName());
            display(message, game);
        }
    }

    /**
     * Display a dialog to notify he purchase.
     *
     * @param info
     * @param game
     */
    private void display(String info, Game game) {
        Context context = Custopoly.getAppContext();

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(info).show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
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
