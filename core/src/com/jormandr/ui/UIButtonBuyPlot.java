package com.jormandr.ui;


import com.badlogic.gdx.Gdx;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.gameworld.GameWorld.GameState;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;

public class UIButtonBuyPlot extends UIButton{
	
	public UIButtonBuyPlot(float x, float y, ButtonType type, GameWorld world){
		super(x,y, type, world);

	}
	
	
	@Override
	public boolean isTouchDown() {

		//run logic for the button being pressed
		GameState gameState = myWorld.getGameState();
		Plot plot = (Plot)InputHandler.getTile();
		Player player = GameWorld.getPlayer(gameState);
		
		if (plot.getOwned() == null){
		
		if (player.getChangeMoney(-plot.getCost()) >= 0){
		
		player.addPlot(plot);
		plot.setOwned(player);
		player.changeMoney(-(plot.getCost()));
			isPressed = true;
			return true;
		}
		}
		
		return false;
	}

}

