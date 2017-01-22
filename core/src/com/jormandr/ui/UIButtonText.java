package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;

public abstract class UIButtonText extends UIButton{
	int initX, initY;
	public UIButtonText(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);
		initX = (int) x;
		initY = (int) y;
		
	}
	
	
	private void drawText(SpriteBatch batcher){
		AssetLoader.fontX.draw(batcher, "This",initX,initY);
		AssetLoader.fontX.draw(batcher,"is placeholder",initX,initY);
	}

}
