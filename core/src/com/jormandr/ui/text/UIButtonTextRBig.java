package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;

/**
 * Abstract class for all text buttons which use the RBIG button design
 *
 */
public abstract class UIButtonTextRBig extends UIButtonText {

	/**
	 * Initialises the UIButtonTextRBig button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 * @param lineOne
	 * @param lineTwo
	 */
	public UIButtonTextRBig(float x, float y, GameWorld world, String lineOne, String lineTwo) {
		super(x, y, world, ButtonType.RBIG, lineOne, lineTwo);

	}

	@Override
	protected void drawText(SpriteBatch batcher) {
		AssetLoader.fontX.draw(batcher, stringOne, initX + 32, initY + 32);
		AssetLoader.fontX.draw(batcher, stringTwo, initX + 32, initY + 10 + 32);
	}

}
