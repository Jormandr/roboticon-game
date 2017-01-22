package com.jormandr.config;

/**
 * Within the config class, configuration settings such as size of screen and
 * game size can be altered
 */
public class GameConfig {

	// The use of methods for constants here is future-proofing for if they ever
	// need to be dynamically calculated. They are not finals

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
		return 2;
	}

	/**
	 * returns horizontal grid of game map size
	 * 
	 * @return horizontal grid of game map size
	 */

	public static int getMapWidth() {
		return 2;
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
	 * This is the maximum value of ore a tile can randomly start with
	 * 
	 * @return
	 */
	public static int getOreValueRandomLimit() {
		return 50;
	}

	/**
	 * This is the maximum value of food a tile can randomly start with
	 * 
	 * @return
	 */
	public static int getFoodValueRandomLimit() {
		return 50;
	}

	/**
	 * This is the maximum value of energy a tile can randomly start with
	 * 
	 * @return
	 */
	public static int getEnergyValueRandomLimit() {
		return 50;
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