package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;

/**
 * Abstract parent class for all buttons that draw text on themselves
 *
 */
public abstract class UIButtonText extends UIButton {
	protected int initX;
	protected int initY;
	String stringOne, stringTwo;

	/**
	 * Initialises the UIButtonText button
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param world
	 */
	public UIButtonText(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);
		initX = (int) x;
		initY = (int) y;

	}

	/**
	 * Initialises the UIButtonText button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 * @param type
	 * @param lineOne
	 * @param lineTwo
	 */
	public UIButtonText(float x, float y, GameWorld world, ButtonType type, String lineOne, String lineTwo) {
		super(x, y, type, world);
		initX = (int) x;
		initY = (int) y;
		stringOne = lineOne;
		stringTwo = lineTwo;
	}

	@Override
	public void draw(SpriteBatch batcher) {
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}

		drawText(batcher);
	}

	/**
	 * method which draws text on button
	 * <p>
	 * should always be called in the draw method of the button
	 * 
	 * @param batcher
	 */
	protected void drawText(SpriteBatch batcher) {
		AssetLoader.fontX.draw(batcher, stringOne, initX, initY);
		AssetLoader.fontX.draw(batcher, stringTwo, initX, initY + 10); //add 10 in y to go down a line
	}

}
