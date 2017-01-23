package com.jormandr.ui;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSSmall;

public class UIButtonEndPhase extends UIButtonTextSSmall{

	public UIButtonEndPhase(float x, float y,GameWorld world) {
		super(x, y, world, "End", "Phase");

	}


	@Override
	public boolean isTouchDown() {
		if (myWorld.isRunning()) {
			Player player = GameWorld.getPlayer(myWorld.getGameState());
			isPressed = true;
			GameWorld.setTimer(30);
			player.nextState();
			return true;
		}
		return false;

	}

}
