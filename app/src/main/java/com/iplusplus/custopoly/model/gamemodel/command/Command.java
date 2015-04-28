package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

import java.io.Serializable;

public interface Command extends Serializable {

    public void execute(Game game);

}
