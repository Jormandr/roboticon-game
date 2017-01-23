package com.jormandr.ui.text.rbig;

import com.badlogic.gdx.Gdx;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.text.UIButtonTextRBig;
/**
* Button which allows players to end the game
 *
 */
public class UIButtonEndGame extends UIButtonTextRBig {
	/**
	 * Initialises the UIButtonEndGame button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonEndGame(float x, float y, GameWorld world) {
		super(x, y, world, "End Game", "");

	}

	@Override
	public boolean isTouchDown() {
		Gdx.app.exit(); //quit game
		return true;
	}

}
