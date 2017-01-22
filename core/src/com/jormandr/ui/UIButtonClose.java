package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.misctypes.Pair;

public class UIButtonClose extends UIButton {

	public UIButtonClose(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);

	}

	@Override
	public void draw(SpriteBatch batcher) {
		Pair<Integer, Integer> mousePos = GameWorld.getMousePos();

		if (isMouseOver(mousePos.x, mousePos.y)) {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}
	}

	@Override
	public boolean isTouchDown() {
		// run logic for the button being pressed
		myWorld.toRunning();
		isPressed = true;
		return true;
	}

}
