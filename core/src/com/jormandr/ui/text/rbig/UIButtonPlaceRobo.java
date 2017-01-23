package com.jormandr.ui.text.rbig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextRBig;
/**
* Button which allows players to place a roboticon on a plot
 *
 */
public class UIButtonPlaceRobo extends UIButtonTextRBig {
	/**
	 * Initialises the UIButtonPlaceRobo button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonPlaceRobo(float x, float y, GameWorld world) {
		super(x, y, world, "", "");

	}

	@Override
	protected void drawText(SpriteBatch batcher) {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Plot plot = ((Plot) InputHandler.getTile());
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
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.hasRoboticon() == false && plot.getOwned() == player) {
			if (player.getRoboticonsOwned() >= 1) {
				plot.setEnergyBuff(1.2f); // set some buff here so that the plot realises it has a roboticon on it (bit hacky)
				player.changeRoboticonsOwned(-1);
				isPressed = true;
				return true;
			}
		}

		return false;
	}

}
