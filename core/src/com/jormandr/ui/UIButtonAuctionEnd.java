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

public class UIButtonAuctionEnd extends UIButton {

	public UIButtonAuctionEnd(float x, float y, ButtonType type, GameWorld world) {
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


			AssetLoader.fontX.draw(batcher, "End Auction ", 590, 493);
			AssetLoader.fontX.draw(batcher, "Turn ", 600, 502);
	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		if (1 == GameWorld.getPlayer(myWorld.getGameState()).getPlayerNumber()) {
				myWorld.setGameState(GameState.AUCTIONP2);
				isPressed = true;
				return true;
			}
		else if (2 == GameWorld.getPlayer(myWorld.getGameState()).getPlayerNumber()) {
			myWorld.setGameState(GameState.ENDCHECK);
			myWorld.toRunning();
			isPressed = true;
			return true;
		} 

		return false;
	}

}
