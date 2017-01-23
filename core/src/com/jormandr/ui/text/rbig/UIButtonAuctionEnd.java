package com.jormandr.ui.text.rbig;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.gameworld.GameWorld.GameState;

import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextRBig;

/**
* Button which allows players to end their auction phase
 *
 */
public class UIButtonAuctionEnd extends UIButtonTextRBig {

	String stringOne, stringTwo;

	/**
	 * Initialises the UIButtonAuctionEnd button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonAuctionEnd(float x, float y, GameWorld world) {
		super(x, y, world, "End Auction", "Turn");

	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		//depending on which player, changes what happens
		
		//currently this logic doesn't have the option of looping, but
		//could in fact be extended to allow for trading between the players
		//as they could set the state back to the other player's
		// and with the addition of some more buttons allowing for trade functionality
		
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
