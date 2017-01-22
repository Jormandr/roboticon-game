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



public class UIButtonBuyOre extends UIButton{
	int initX,initY;	
	public UIButtonBuyOre(float x, float y, ButtonType type, GameWorld world){
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
			AssetLoader.fontX.draw(batcher, "Buy",initX+16,initY+25);
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
		int cost = -myMarket.getOreSellValue();
		//run logic for the button being pressed
		if (myMarket.getOre() > 0){
		if (player.getChangeMoney(cost) >= 0){
		player.changeOre(10);
		player.changeMoney(cost);
		myMarket.changeOre(-1);
			isPressed = true;
			return true;
		}

	}
		return false;
	}
}

