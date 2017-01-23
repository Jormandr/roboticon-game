package com.jormandr.ui.trade;

import javax.annotation.Resource;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSBig;

public class UIButtonBuy extends UIButtonTrade {

	ResourceType resource;

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
		Market myMarket = myWorld.getMarket();
		int cost = -getResource();
		//run logic for the button being pressed
		if (getResourceValue() > 0){
		if (player.getChangeMoney(cost) >= 0){
		changeResource(10);
		player.changeMoney(cost);
		changeResourceValue(-10); 
			isPressed = true;
			return true;
		}

	}
		return false;
	}

}
