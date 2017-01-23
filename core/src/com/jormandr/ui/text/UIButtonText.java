package com.jormandr.ui.text;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;

public abstract class UIButtonText extends UIButton{
	int initX, initY;
	String stringOne, stringTwo;
	public UIButtonText(float x, float y, ButtonType type, GameWorld world) {
		super(x, y,type , world);
		initX = (int) x;
		initY = (int) y;

		
	}
	
	public UIButtonText(float x, float y, GameWorld world,ButtonType type, String lineOne,String lineTwo){ 
		super(x, y, type ,world);
		initX = (int) x;
		initY = (int) y;
		stringOne = lineOne;
		stringTwo = lineTwo;
	}
	
	

	
	@Override
	public void draw(SpriteBatch batcher) {
		// Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}
		
		drawText(batcher);
	}

	
	protected void drawText(SpriteBatch batcher){
		AssetLoader.fontX.draw(batcher, stringOne,initX,initY);
		AssetLoader.fontX.draw(batcher,stringTwo,initX,initY+10);
	}


}
