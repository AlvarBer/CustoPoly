package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

public class MortgageCommand implements Command, DialogInterface.OnClickListener {

    private PropertyLand property;
    private boolean mortOrNot;

    public MortgageCommand(PropertyLand property) {
        this.property = property;
    }

    @Override
    public void execute(Game game) {
        Context context = Custopoly.getAppContext();
        Player player = game.getCurrentPlayer();
        if (player.getProperties().contains(property)) {
            askMortgage(player, context.getText(R.string.ingame_mortgage).toString());
        } else if (player.getMortgagedProperties().contains(property)) {
            askUnmortgage(player, context.getText(R.string.ingame_unmortgage).toString());
        }
    }

    private void askMortgage(Player player, String mortgage) {
        Context context = Custopoly.getAppContext();
        boolean reply = ask(player, mortgage, context);
        doCommand(player, reply, mortgage, context);
    }

    private void askUnmortgage(Player player, String unmortgage) {
        Context context = Custopoly.getAppContext();
        boolean reply = ask(player, unmortgage, context);
        doCommand(player, reply, unmortgage, context);
    }

    /**
     * Interpret the result of asking a player about his mortgage.
     *
     * @param player
     * @param reply
     * @param command
     */
    private void doCommand(Player player, boolean reply, String command, Context context) {

        if (reply == true) {
            if (command.equals(context.getText(R.string.ingame_mortgage).toString())) {
                player.getProperties().remove(property);
                player.getMortgagedProperties().add(property);
                player.increaseBalance(property.getMortgage());
            } else if (command.equals(context.getText(R.string.ingame_unmortgage).toString())) {
                player.getProperties().add(property);
                player.getMortgagedProperties().remove(property);
                player.decreaseBalance(property.getMortgage());
            }

            String message = String.format(context.getText(R.string.ingame_mortgage_donemsg).toString(),
                    property.getName(), command, property.getMortgage());

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(command).setMessage(message).show();

        } else {
            String message = String.format(context.getText(R.string.ingame_mortgage_failmsg).toString(), command);

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(command).setMessage(message).show();
        }

    }

    /**
     * Show a dialog to ask if the player wants to mortgage/unmortgage something.
     *
     * @param player
     * @param command
     * @return
     */
    private boolean ask(Player player, String command, Context context) {

        String title = player.getName();
        String message = String.format(
                context.getText(R.string.ingame_askMortgageMessage).toString(),
                command, property.getName(), property.getMortgage());

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title).setMessage(message).setPositiveButton(R.string.ingame_mortgageYes, this).setNegativeButton(R.string.ingame_mortgageNo, this).show();

        return this.mortOrNot;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                this.mortOrNot = true;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                this.mortOrNot = false;
                break;
        }
    }
}
