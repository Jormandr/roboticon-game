package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.gameworld.GameWorld.GameState;
import com.jormandr.gameworld.GameWorld.WorldState;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;

public class UIButtonEndGame extends UIButton {

	public UIButtonEndGame(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);

	}

	@Override
	public void draw(SpriteBatch batcher) {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Plot plot = ((Plot) InputHandler.getTile());

		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}


			AssetLoader.fontX.draw(batcher, "Close Game ", 590, 493);
	}

	@Override
	public boolean isTouchDown() {
		Gdx.app.exit();
		 return true;
	}

}
