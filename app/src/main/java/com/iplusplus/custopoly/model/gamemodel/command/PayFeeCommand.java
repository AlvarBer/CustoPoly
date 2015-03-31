package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class PayFeeCommand implements Command {

    private int fee;

    public PayFeeCommand(int fee) {
        this.fee = fee;
    }

    @Override
    public void execute(Game game, Context context) {
        Player player = game.getCurrentPlayer();
        player.decreaseBalance(fee);
        String message = String.format(context.getText(R.string.ingame_feepaidmsg).toString(), player.getName(), fee);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(message).show();
    }
}
