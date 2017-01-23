package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;

public abstract class UIButtonTextSBig  extends UIButtonText{

	public UIButtonTextSBig(float x, float y, GameWorld world, String lineOne, String lineTwo) {
		super(x, y, world, ButtonType.SBIG ,lineOne, lineTwo);

		
	}
	
	@Override
	protected void drawText(SpriteBatch batcher){
		AssetLoader.fontX.draw(batcher, stringOne,initX+32,initY+32);
		AssetLoader.fontX.draw(batcher,stringTwo,initX+32,initY+10+32);
	}
	
	

}
