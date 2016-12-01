package com.jormandr.roboticongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.screens.GameScreen;

public class RoboticonGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("RoboticonGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
