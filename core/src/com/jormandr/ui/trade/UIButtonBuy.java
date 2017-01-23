package com.jormandr.ui.trade;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;
/**
* Button which allows players to buy resources from the market
 *
 */
public class UIButtonBuy extends UIButtonTrade {


	ResourceType resource;

	/**
	 * Initialises the UIButtonBuy button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 * @param sauce
	 */
	public UIButtonBuy(float x, float y, GameWorld world, ResourceType sauce) {
		super(x, y, world, sauce);

		resource = sauce;

	}

	@Override
	protected void drawText(SpriteBatch batcher) {
		
		// DOn't autoformat this; it looks nice rn
		String resStr = resource == ResourceType.FOOD ? "Food"
				      : resource == ResourceType.ORE ? "Ore"
				      : resource == ResourceType.ENERGY ? "Energy"
				      : "UNDEFINED";

		if (getResourceValue() > 0) {
			AssetLoader.fontX.draw(batcher, "Buy", initX + 16, initY + 25);
			AssetLoader.fontX.draw(batcher, resStr, initX + 10, initY + 35);
		} else {
			AssetLoader.fontX.draw(batcher, "Out of", initX + 14, initY + 25);
			AssetLoader.fontX.draw(batcher, "Stock", initX + 16, initY + 35);
		}
	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		int cost = -getResource();
		if (getResourceValue() > 0) {
			if (player.getChangeMoney(cost) >= 0) {
				changeResource(QUANTITY); //add resource to player
				player.changeMoney(cost); //remove money from player
				changeResourceValue(-QUANTITY); // remove resource from market
				isPressed = true;
				return true;
			}

		}
		return false;
	}

}
