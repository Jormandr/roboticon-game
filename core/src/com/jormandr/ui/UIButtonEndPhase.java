package com.jormandr.ui;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSSmall;
/**
 * Button which allows players to end the current phase
 * and move to the next
 *
 */
public class UIButtonEndPhase extends UIButtonTextSSmall {

	/**
	 * Initialises the UIButtonEndPhase button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonEndPhase(float x, float y, GameWorld world) {
		super(x, y, world, "End", "Phase");

	}

	@Override
	public boolean isTouchDown() {
		if (myWorld.isRunning()) { //only possible to go the next phase while not in a menu
			Player player = GameWorld.getPlayer(myWorld.getGameState());
			isPressed = true;
			GameWorld.setTimer(30);
			player.nextState();
			return true;
		}
		return false;

	}

}
