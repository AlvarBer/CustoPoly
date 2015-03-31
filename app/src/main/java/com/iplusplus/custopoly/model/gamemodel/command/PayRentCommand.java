package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

public class PayRentCommand implements Command {

    @Override
    public void execute(Game game, Context context) {
        PropertyLand property = (PropertyLand) getCurrentLand(game);
        int payment = property.acceptCalculator(new RentCalculator(game));
        makeTransaction(game, context, payment);
    }

    protected Land getCurrentLand(Game game) {
        return game.getBoard().getLands().get(game.getCurrentPlayer().getToken().getLandIndex());
    }

    private void makeTransaction(Game game, Context context, int payment) {
        Player source = game.getCurrentPlayer();
        Player target = game.getOwner((PropertyLand) getCurrentLand(game));
        if (!source.equals(target)) {
            source.decreaseBalance(payment);
            target.increaseBalance(payment);
            String message = String.format(context.getText(R.string.ingame_rentpaidmsg).toString(), source.getName(), payment, target.getName());

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage(message).show();
        }
    }
}
