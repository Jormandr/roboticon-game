package com.jormandr.config;

/**
 * Within the config class, configuration settings such as size of screen and
 * game size can be altered
 */
public class GameConfig {

	// float screenWidth = Gdx.graphics.getWidth();
	// float screenHeight = Gdx.graphics.getHeight();

	/**
	 * returns view width
	 * 
	 * @return view width
	 */

	public static int getWidth() {
		return 1280;
	}

	/**
	 * returns view height
	 * 
	 * @return view height
	 */

	public static int getHeight() {
		return 720;
	}

	/**
	 * returns vertical grid of game map size
	 * 
	 * @return vertical grid of game map size
	 */

	public static int getMapHeight() {
		return 5;
	}

	/**
	 * returns horizontal grid of game map size
	 * 
	 * @return horizontal grid of game map size
	 */

	public static int getMapWidth() {
		return 5;
	}

	/**
	 * returns random event chance
	 * 
	 * @return random event chance
	 */

	public static int getRandomEventChance() {
		return 10; // 1-in
	}

	public static float getTileWidth() {
		return 64;
	}

	public static float getTileHeight() {
		return 32;
	}

}
