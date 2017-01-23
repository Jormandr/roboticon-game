package com.jormandr.ui.trade;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;

public class UIButtonSell extends UIButtonTrade {

	ResourceType resource;

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
		// run logic for the button being pressed
		if (getChangeResource(-10) >= 0) {
			changeResource(-10);
			player.changeMoney(cost);
			changeResourceValue(10);
			isPressed = true;
			return true;
		}
		return false;
	}

}
