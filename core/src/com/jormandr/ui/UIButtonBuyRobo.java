package com.jormandr.ui;

import com.jormandr.gameobjects.Market;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSBig;

public class UIButtonBuyRobo extends UIButtonTextSBig {
	int initX, initY;

	public UIButtonBuyRobo(float x, float y, GameWorld world) {
		super(x, y, world, "Buy", "Roboticon");

	}
	

	/*
	 * THis doesn't work right now, not 100% necessary but nice polish to show when out of stock 
	@Override
	protected void drawText(SpriteBatch batcher){
		//not drawing correctly
		Market myMarket = myWorld.getMarket();
		if (myMarket.getRoboticons() > 0){
			AssetLoader.fontX.draw(batcher, "Buy",initX+16,initY+25);
			AssetLoader.fontX.draw(batcher,"Roboticon",initX+10,initY+35);
	}
		else{
			AssetLoader.fontX.draw(batcher, "Out of",initX+14,initY+25);
			AssetLoader.fontX.draw(batcher,"Stock",initX+16,initY+35);
		}
	}
	*/

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Market myMarket = myWorld.getMarket();
		int cost = -myMarket.getRoboticonSellValue();
		// run logic for the button being pressed
		if (myMarket.getRoboticons() > 0) {
			if (player.getChangeMoney(cost) >= 0) {
				player.changeRoboticonsOwned(1);
				player.changeMoney(cost);
				myMarket.changeRoboticons(-1);
				isPressed = true;
				return true;
			}
		}
		return false;
	}

}

