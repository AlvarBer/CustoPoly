package com.iplusplus.custopoly.model.gamemodel.command;

import android.content.Context;
import com.iplusplus.custopoly.model.gamemodel.element.Game;

public interface Command {
		
	public void execute(Game game, Context context);

}
