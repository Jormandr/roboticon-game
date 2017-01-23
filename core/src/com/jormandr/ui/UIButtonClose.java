package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.misctypes.Pair;

/**
 *Button which allows players to close menus
 *
 */
public class UIButtonClose extends UIButton {

	/**
	 * initialises the UIButtonClose button
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param world
	 */
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
		myWorld.toRunning(); //closes the menu
		isPressed = true;
		return true;
	}

}
