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
		return 7;
	}

	/**
	 * returns horizontal grid of game map size
	 * 
	 * @return horizontal grid of game map size
	 */

	public static int getMapWidth() {
		return 7;
	}

	/**
	 * returns random event chance
	 * 
	 * @return random event chance
	 */

	public static int getRandomEventChance() {
		return 10; // 1-in
	}

	/**
	 * returns width of one tile
	 * 
	 * @return width of one tile
	 */
	public static float getTileWidth() {
		return 64;
	}

	/**
	 * returns height of one tile
	 * 
	 * @return height of one tile
	 */
	public static float getTileHeight() {
		return 32;
	}

}