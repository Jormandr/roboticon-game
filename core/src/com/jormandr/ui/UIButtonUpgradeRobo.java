package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.misctypes.Pair;
import com.jormandr.players.Player;

public abstract class UIButtonUpgradeRobo extends UIButton {

	public UIButtonUpgradeRobo(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);
	}

	@Override
	public void draw(SpriteBatch batcher) {
		Plot plot = ((Plot) InputHandler.getTile());
		Pair<Integer, Integer> mousePos = GameWorld.getMousePos();
		// Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}

		if (isMouseOver(mousePos.x, mousePos.y)) {
			AssetLoader.fontX.draw(batcher, "Cost: " + plot.getCost(), 830, 274);
		} else if (((Plot) InputHandler.getTile()).hasRoboticon() == true) {
			AssetLoader.fontX.draw(batcher, "Upgrade XXX ", 820, 270);
			AssetLoader.fontX.draw(batcher, "production", 820, 279);
		} else {
			AssetLoader.fontX.draw(batcher, "Place Roboticon", 820, 270);
			AssetLoader.fontX.draw(batcher, "to Upgrade XXX", 820, 279);
		}
	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		// run logic for the button being pressed
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.hasRoboticon() == true && plot.getOwned() == player) {
			Gdx.app.log("InputHandler: ", "Button Clicked");
			if (player.getChangeMoney(-plot.getCost()) >= 0) {

				plot.setEnergyBuff(2.0f); // set some buff here
				player.changeMoney(-(plot.getCost()));
				isPressed = true;
				return true;
			}
		}

		return false;
	}

}
