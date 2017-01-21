package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;

public class UIButtonEndTurn extends UIButton{

	public UIButtonEndTurn(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);
		
		
	}
	
	@Override
	public void draw(SpriteBatch batcher) {
		//Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		}

		AssetLoader.fontX.draw(batcher, "End",766, 40);
		AssetLoader.fontX.draw(batcher, "Turn",766, 50);	
	}

	
	
	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		isPressed = true;
		player.nextState();
		return true;

	}

	
	

}
