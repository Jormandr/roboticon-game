package com.jormandr.ui.trade;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;
/**
* Button which allows players to sell resources to the market
 *
 */
public class UIButtonSell extends UIButtonTrade {

	ResourceType resource;

	/**
	 * Initialises the UIButtonSell button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 * @param sauce
	 */
	public UIButtonSell(float x, float y, GameWorld world, ResourceType sauce) {
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

		AssetLoader.fontX.draw(batcher, "Sell", initX + 16, initY + 25);
		AssetLoader.fontX.draw(batcher, resStr, initX + 10, initY + 35);

	}

	//over-riding the parent class to swap out sell value with buy value 
	
	@Override
	protected int getResource() {
		int cost;
		switch (resource) {
		case FOOD:
			cost = Market.getFoodBuyValue();
			break;
		case ORE:
			cost = Market.getOreBuyValue();
			break;
		case ENERGY:
			cost = Market.getEnergyBuyValue();
			break;
		default:
			cost = 0;
			break;
		}
		return cost;
	}

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		int cost = getResource();
		if (getChangeResource(-QUANTITY) >= 0) {
			changeResource(-QUANTITY); //remove resource from player
			player.changeMoney(cost); //add money to player
			changeResourceValue(QUANTITY); //add resource to market
			isPressed = true;
			return true;
		}
		return false;
	}

}
