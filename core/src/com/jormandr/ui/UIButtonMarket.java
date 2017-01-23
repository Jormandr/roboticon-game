package com.jormandr.ui;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.InputHandler;
import com.jormandr.helpers.InputHandler.MenuUI;
import com.jormandr.ui.text.UIButtonTextSSmall;

/**
 * Button which allows players to open the market menu
 *
 */
public class UIButtonMarket extends UIButtonTextSSmall {

	/**
	 * Initialises the UIButtonMarket button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonMarket(float x, float y, GameWorld world) {
		super(x, y, world, "Market", "");

	}

	@Override
	public boolean isTouchDown() {
		if (myWorld.isRunning()) { //only possible to go the market whilst not in another menu
			isPressed = true;
			InputHandler.setMenu(MenuUI.MARKET);
			myWorld.toMenuMarket();
			return true;
		}
		return false;

	}

}
