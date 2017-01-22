package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;

public class UIButtonPlaceRobo extends UIButton {

	public UIButtonPlaceRobo(float x, float y, ButtonType type, GameWorld world) {
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

		if (plot.getOwned() != player) {
			AssetLoader.fontX.draw(batcher, "You do not", 590, 493);
			AssetLoader.fontX.draw(batcher, "own this tile", 590, 502);
		} else if (plot.hasRoboticon() == false) {
			AssetLoader.fontX.draw(batcher, "Place Roboticon", 595, 498);
		} else {
			AssetLoader.fontX.draw(batcher, "Already Placed ", 590, 493);
			AssetLoader.fontX.draw(batcher, "Roboticon ", 590, 502);
		}
	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		// run logic for the button being pressed
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.hasRoboticon() == false && plot.getOwned() == player) {
			if (player.getRoboticonsOwned() >= 1) {
				Gdx.app.log("InputHandler: ", "Button Clicked");
				plot.setEnergyBuff(2.0f); // set some buff here
				player.changeRoboticonsOwned(-1);
				isPressed = true;
				return true;
			}
		}

		return false;
	}

}
