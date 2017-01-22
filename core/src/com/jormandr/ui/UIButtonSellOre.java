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



public class UIButtonSellOre extends UIButton{
	int initX,initY;
	public UIButtonSellOre(float x, float y, ButtonType type, GameWorld world){
		super(x,y, type, world);
		initX = (int) x;
		initY = (int) y;

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
		
		if (myMarket.getFood() > 0){
			AssetLoader.fontX.draw(batcher, "Sell",initX+16,initY+25);
			AssetLoader.fontX.draw(batcher,"Ore",initX+10,initY+35);
	}
		else{
			AssetLoader.fontX.draw(batcher, "Out of",initX+14,initY+25);
			AssetLoader.fontX.draw(batcher,"Stock",initX+16,initY+35);
		}
	}

	
	
	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Market myMarket = myWorld.getMarket();
		int cost = myMarket.getOreBuyValue();
		//run logic for the button being pressed
		if (player.getChangeOre(-10) >= 0){
		player.changeOre(-10);
		myMarket.changeOre(10);
		player.changeMoney(cost);
			isPressed = true;
			return true;
		}
		return false;
	}
}

