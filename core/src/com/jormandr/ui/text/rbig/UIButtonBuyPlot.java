package com.jormandr.ui.text.rbig;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.text.UIButtonTextRBig;

public class UIButtonBuyPlot extends UIButtonTextRBig {

	public UIButtonBuyPlot(float x, float y, GameWorld world) {
		super(x, y, world, "", "");

	}
	
	
	@Override protected void drawText(SpriteBatch batcher){
		Plot plot = ((Plot) InputHandler.getTile());
		
	if (plot.getOwned() == null) {
		AssetLoader.fontX.draw(batcher, "Buy Plot", 630, 498);
	} else {
		AssetLoader.fontX.draw(batcher, "Owned by Player " + plot.getOwned().getPlayerNumber(), 588, 498);
	}
	}


	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		// run logic for the button being pressed
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.getOwned() == null) {
			if (player.getChangeMoney(-plot.getCost()) >= 0) {
				Gdx.app.log("InputHandler: ", "Button Clicked");
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
