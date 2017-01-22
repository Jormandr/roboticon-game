package com.jormandr.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.gameworld.GameWorld.GameState;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;



public class UIButtonBuyRobo extends UIButton{
	
	public UIButtonBuyRobo(float x, float y, ButtonType type, GameWorld world){
		super(x,y, type, world);


	}
	
	@Override
	public void draw(SpriteBatch batcher) {
		Market myMarket = myWorld.getMarket();
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth, buttonHeight, 4, 4,
					0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth, buttonHeight, 4, 4,
					0);
		}
		
		if (myMarket.getRoboticons() > 0){
			AssetLoader.fontX.draw(batcher, "Buy",94*4+16,102*4+25);
			AssetLoader.fontX.draw(batcher,"Roboticon",94*4+10,102*4+35);
	}
		else{
			AssetLoader.fontX.draw(batcher, "Out of",94*4+14,102*4+25);
			AssetLoader.fontX.draw(batcher,"Stock",94*4+16,102*4+35);
		}
	}

	
	
	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Market myMarket = myWorld.getMarket();
		int cost = -myMarket.getRoboticonSellValue();
		//run logic for the button being pressed
		if (myMarket.getRoboticons() > 0){
		if (player.getChangeMoney(cost) >= 0){
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

