package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

public class MortgageCommand implements Command {

    private PropertyLand property;
    private boolean mortOrNot;

    public MortgageCommand(PropertyLand property) {
        this.property = property;
    }

    @Override
    public void execute(Game game) {
        Player player = game.getCurrentPlayer();
        if (player.getProperties().contains(property)) {
            doCommand(player, "mortgage");
        } else if (player.getMortgagedProperties().contains(property)) {
            doCommand(player, "unmortgage");
        }
    }

    /**
     * Interpret the result of asking a player about his mortgage.
     *
     * @param player
     * @param command
     */
    private void doCommand(Player player, String command) {
            if (command.equals("mortgage")) {
                player.getProperties().remove(property);
                player.getMortgagedProperties().add(property);
                player.increaseBalance(property.getMortgage());
            } else if (command.equals("unmortgage")) {
                player.getProperties().add(property);
                player.getMortgagedProperties().remove(property);
                player.decreaseBalance(property.getMortgage());
            }
    }
}
