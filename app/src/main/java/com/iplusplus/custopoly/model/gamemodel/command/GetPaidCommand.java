package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;

public class GetPaidCommand implements Command {

    @Override
    public void execute(Game game) {
        Player player = game.getCurrentPlayer();
        player.increaseBalance(game.PAYMENT);
    }
}
