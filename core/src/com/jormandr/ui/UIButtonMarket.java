package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.helpers.InputHandler.MenuUI;

public class UIButtonMarket extends UIButton {

	public UIButtonMarket(float x, float y, ButtonType type, GameWorld world) {
		super(x, y, type, world);

	}

	@Override
	public void draw(SpriteBatch batcher) {
		// Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}

		AssetLoader.fontX.draw(batcher, "Market", 118*4 +6, 45);
	}

	@Override
	public boolean isTouchDown() {
		if (myWorld.isRunning()) {
			isPressed = true;
			InputHandler.setMenu(MenuUI.MARKET);
			myWorld.toMenuMarket();
			return true;
		}
		return false;

	}

}
