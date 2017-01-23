package com.jormandr.ui.text.rbig;

import com.badlogic.gdx.Gdx;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.text.UIButtonTextRBig;

public class UIButtonEndGame extends UIButtonTextRBig {

	public UIButtonEndGame(float x, float y, GameWorld world) {
		super(x, y, world, "End Game", "");

	}

	@Override
	public boolean isTouchDown() {
		Gdx.app.exit();
		 return true;
	}

}
