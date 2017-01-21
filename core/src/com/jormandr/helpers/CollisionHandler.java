package com.jormandr.helpers;


import com.badlogic.gdx.math.Intersector;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameworld.GameWorld;

/**
 * CollisionHandler handles collision logic of game objects and assets
 *
 */
public class CollisionHandler {
	
	private static int[] mousePos = new int[2];

	// See note from MapTile about guessing

	/**
	 * returns the nearest map tile to the mouse
	 * <p>
	 * if mouse is off map grid it will return the first tile in the map array
	 * 
	 * @return returns the nearest map tile to the mouse
	 */

	public static MapTile getNearestMapTile() {
		float[] gridPos = convertToGrid(mousePos);

		// Gdx.app.log("mouse I", Float.toString(gridPos[0]));
		// Gdx.app.log("mouse J", Float.toString(gridPos[1]));

		MapTile[][] map = GameWorld.getMap();

		MapTile tile = map[0][0];
		if ((gridPos[0] <= 0) || (gridPos[0] >= map.length) || (gridPos[1] <= 0) || (gridPos[1] >= map[1].length)) {
			// Gdx.app.log("grid", "out of array range");
		} else {
			tile = map[(int) gridPos[0]][(int) gridPos[1]];
		}

		return tile;

	}

	/**
	 * Update is run every frame
	 * <p>
	 * Checks mouse for update in inputs
	 */
	public static void update() {
		
		mousePos = GameWorld.getMousePos();

	}

	/**
	 * Converts a vector camera view position into a vector map grid position
	 * 
	 * @param mousePos
	 * @return map grid position
	 */
	private static float[] convertToGrid(int[] mousePos) {
		float w = GameConfig.getTileWidth();
		float h = GameConfig.getTileHeight();
		float[] gridPos = new float[2];
		float x = mousePos[0] - 10.0f * w;
		float y = mousePos[1] - 7.0f * h;
		// the reason this is shit is because the rendering of the tiles is
		// offset to be centred in the screen but the actual position is at 0,0
		// screen position
		gridPos[0] = ((x / w) + (y / h)) / 2;
		gridPos[1] = ((y / h) - (x / w)) / 2;

		return gridPos;
	}

	/**
	 * returns whether the mouse is over a map tile
	 * 
	 * @return whether the mouse is over a map tile
	 */
	public static boolean tileMouseOver() {

		MapTile tile = getNearestMapTile();

		float[] tileV = tile.getVerts();

		return (Intersector.isPointInPolygon(tileV, 0, 8, mousePos[0], mousePos[1]));
	}

}
