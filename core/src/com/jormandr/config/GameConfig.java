package com.jormandr.config;

import com.badlogic.gdx.Gdx;

public class GameConfig {

	// float screenWidth = Gdx.graphics.getWidth();
	// float screenHeight = Gdx.graphics.getHeight();

	public static int getWidth() {
		return 1280;
	}

	public static int getHeight() {
		return 720;
	}

	public static int getMapHeight() {
		return 5;
	}

	public static int getMapWidth() {
		return 5;
	}

	public static int getRandomEventChance() {
		return 10; // 1-in
	}

}
