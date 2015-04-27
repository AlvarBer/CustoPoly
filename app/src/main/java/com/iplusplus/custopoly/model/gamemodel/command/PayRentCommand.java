package com.iplusplus.custopoly.model.gamemodel.command;

import android.app.AlertDialog;
import android.content.Context;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Land;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.util.RentCalculator;

public class PayRentCommand implements Command {

    @Override
    public void execute(Game game) {
        PropertyLand property = (PropertyLand) getCurrentLand(game);
        int payment = property.acceptCalculator(new RentCalculator(game));
        makeTransaction(game, payment);
    }

    protected Land getCurrentLand(Game game) {
        return game.getBoard().getLands().get(game.getCurrentPlayer().getLandIndex());
    }

    private void makeTransaction(Game game, int payment) {
        Context context = Custopoly.getAppContext();
        Player source = game.getCurrentPlayer();
        Player target = game.getOwner((PropertyLand) getCurrentLand(game));
        if (!source.equals(target)) {
            source.decreaseBalance(payment);
            target.increaseBalance(payment);
            String message = String.format(context.getText(R.string.ingame_rentpaidmsg).toString(),
                                            source.getName(), payment, target.getName());

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage(message).show();
        }
    }
}
