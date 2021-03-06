package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;

/**
 * Abstract class for all text buttons which use the SBIG button design
 *
 */
public abstract class UIButtonTextSBig extends UIButtonText {

	/**
	 * Initialises the UIBUttonTextSBig button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 * @param lineOne
	 * @param lineTwo
	 */
	public UIButtonTextSBig(float x, float y, GameWorld world, String lineOne, String lineTwo) {
		super(x, y, world, ButtonType.SBIG, lineOne, lineTwo);

	}

	@Override
	protected void drawText(SpriteBatch batcher) {
		AssetLoader.fontX.draw(batcher, stringOne, initX + 14, initY + 25);
		AssetLoader.fontX.draw(batcher, stringTwo, initX + 16, initY + 10 + 25);
	}

}
