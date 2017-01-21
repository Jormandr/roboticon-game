package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;

public class UIButtonClose extends UIButton{
	
	
	public UIButtonClose(float x, float y, ButtonType type, GameWorld world){
		super(x,y, type, world);

	}
	
	@Override
	public void draw(SpriteBatch batcher) {
		int[] mousePos = myWorld.getMousePos();
		
		if (isMouseOver(mousePos[0],mousePos[1])) {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		}
	}
	
	@Override
	public boolean isTouchDown() {
		//run logic for the button being pressed
		myWorld.toRunning();
		isPressed = true;
			return true;
	}

}
