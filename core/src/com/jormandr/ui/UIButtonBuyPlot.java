package com.jormandr.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player;

public class UIButtonBuyPlot extends UIButton {

	public UIButtonBuyPlot(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);

	}

	@Override
	public void draw(SpriteBatch batcher) {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		// Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}

		if (((Plot) InputHandler.getTile()).getOwned() == null) {
			AssetLoader.fontX.draw(batcher, "Buy", 630, 498);
		} else {
			AssetLoader.fontX.draw(batcher, "Owned by Player " + player.getPlayerNumber(), 588, 498);
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
