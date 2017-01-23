package com.jormandr.ui.upgrade;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.misctypes.Pair;
import com.jormandr.players.Player;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;

/**
 * Abstract class for buttons that upgrade roboticons
 *
 */
public abstract class UIButtonUpgradeRobo extends UIButton {

	/**
	 * Initialises the UIBUttonUpgradeRobo button
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param world
	 */
	public UIButtonUpgradeRobo(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);
	}

	@Override
	public void draw(SpriteBatch batcher) {
		Plot plot = ((Plot) InputHandler.getTile());
		Pair<Integer, Integer> mousePos = GameWorld.getMousePos();
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
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.hasRoboticon() == true && plot.getOwned() == player) {
			if (player.getChangeMoney(-plot.getCost()) >= 0) {
				 // set some buff here
				player.changeMoney(-(plot.getCost()));
				isPressed = true;
				plot.setCost(); //increase cost of upgrading
				return true;
			}
		}

		return false;
	}

}
