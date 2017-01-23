package com.jormandr.ui.upgrade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.misctypes.Pair;
import com.jormandr.players.Player;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;

public class UIButtonUpgradeRoboEnergy extends UIButton {

	int initX, initY;

	public UIButtonUpgradeRoboEnergy(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);

		initX = (int) x;
		initY = (int) y;
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
			AssetLoader.fontX.draw(batcher, "Cost: " + plot.getCost(), initX + 34, initY + 18);
		} else if (((Plot) InputHandler.getTile()).hasRoboticon() == true) {
			AssetLoader.fontX.draw(batcher, "Upgrade energy ", initX + 16, initY + 14);
			AssetLoader.fontX.draw(batcher, "production", initX + 16, initY + 23);
		} else {
			AssetLoader.fontX.draw(batcher, "Place Roboticon", initX + 16, initY + 14);
			AssetLoader.fontX.draw(batcher, "to Upgrade Energy", initX + 16, initY + 23);
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
