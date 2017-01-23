package com.jormandr.ui;

import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.InputHandler;
import com.jormandr.helpers.InputHandler.MenuUI;
import com.jormandr.ui.text.UIButtonTextSSmall;

public class UIButtonMarket extends UIButtonTextSSmall {

	public UIButtonMarket(float x, float y, GameWorld world) {
		super(x, y, world, "Market", "");

	}

	@Override
	public boolean isTouchDown() {
		if (myWorld.isRunning()) {
			isPressed = true;
			InputHandler.setMenu(MenuUI.MARKET);
			myWorld.toMenuMarket();
			return true;
		}
		return false;

	}

}
