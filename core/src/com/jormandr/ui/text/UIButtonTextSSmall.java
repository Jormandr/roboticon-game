package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;

public abstract class UIButtonTextSSmall extends UIButtonText {

	public UIButtonTextSSmall(float x, float y, GameWorld world, String lineOne, String lineTwo) {
		super(x, y, world, ButtonType.SSMALL, lineOne, lineTwo);

	}

	@Override
	protected void drawText(SpriteBatch batcher) {
		AssetLoader.fontX.draw(batcher, stringOne, initX + 8, initY + 16);
		AssetLoader.fontX.draw(batcher, stringTwo, initX + 10, initY + 10 + 16);
	}

}
