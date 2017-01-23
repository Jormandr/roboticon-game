package com.jormandr.ui.text.rbig;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.gameworld.GameWorld.GameState;

import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextRBig;

public class UIButtonAuctionEnd extends UIButtonTextRBig {

	String stringOne, stringTwo;

	public UIButtonAuctionEnd(float x, float y, GameWorld world) {
		super(x, y, world, "End Auction", "Turn");

	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		if (1 == player.getPlayerNumber()) {
			myWorld.setGameState(GameState.AUCTIONP2);
			isPressed = true;
			return true;
		} else if (2 == player.getPlayerNumber()) {
			myWorld.setGameState(GameState.ENDCHECK);
			myWorld.toRunning();
			isPressed = true;
			return true;
		}

		return false;
	}

}
