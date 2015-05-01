package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.gamemodel.Observer.GameObserver;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class PayFeeCommand implements Command {

    private int fee;

    public PayFeeCommand(int fee) {
        this.fee = fee;
    }

    @Override
    public void execute(Game game) {
        Context context = Custopoly.getAppContext();
        Player player = game.getCurrentPlayer();
        player.decreaseBalance(fee);
        String message = String.format(context.getText(R.string.ingame_feepaidmsg).toString(), player.getName(), fee);

        for (GameObserver o: game.getObserversList())
            o.onPayFee(message);
    }
}
