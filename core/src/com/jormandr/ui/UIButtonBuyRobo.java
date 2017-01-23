package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSBig;

/**
 * Button which allows players to buy roboticons
 *
 */
public class UIButtonBuyRobo extends UIButtonTextSBig {

	/**
	 * initialises the UIButtonBuyRobo button
	 * 
	 * @param x
	 * @param y
	 * @param world
	 */
	public UIButtonBuyRobo(float x, float y, GameWorld world) {
		super(x, y, world, "", "");

	}
	


	@Override
	protected void drawText(SpriteBatch batcher){
		if (Market.getRoboticons() > 0){
			AssetLoader.fontX.draw(batcher, "Buy",initX+16,initY+25);
			AssetLoader.fontX.draw(batcher,"Roboticon",initX+10,initY+35);
	}
		else{
			AssetLoader.fontX.draw(batcher, "Out of",initX+14,initY+25);
			AssetLoader.fontX.draw(batcher,"Stock",initX+16,initY+35);
		}
	}


	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		int cost = -Market.getRoboticonSellValue();
		if (Market.getRoboticons() > 0) {
			if (player.getChangeMoney(cost) >= 0) { //makes sure player has the money to purchase
				player.changeRoboticonsOwned(1); //add roboticon to player inventory
				player.changeMoney(cost);
				Market.changeRoboticons(-1); //remove robotion from market inventory
				isPressed = true;
				return true;
			}
		}
		return false;
	}

}

