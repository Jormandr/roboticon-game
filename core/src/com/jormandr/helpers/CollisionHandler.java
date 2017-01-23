package com.jormandr.helpers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.misctypes.Pair;

/**
 * CollisionHandler handles collision logic of game objects and assets
 *
 */
public class CollisionHandler {

	private static Pair<Integer, Integer> mousePos;

	// See note from MapTile about guessing

	/**
	 * returns the nearest map tile to the mouse
	 * <p>
	 * if mouse is off map grid it will return the first tile in the map array
	 * 
	 * @return returns the nearest map tile to the mouse
	 */

	public static MapTile getNearestMapTile() {
		Vector2 gridPos = convertToGrid(mousePos);

		MapTile[][] map = GameWorld.getMap();

		MapTile tile = map[0][0]; //default case returns map tile at 0,0
									// this means tileMouseOver will return false
		
		if (!((gridPos.x <= 0) || (gridPos.x >= map.length) || (gridPos.y <= 0) || (gridPos.y >= map[1].length))) {
			tile = map[(int) gridPos.x][(int) gridPos.y];
		}

		return tile;
	}

	/**
	 * Update is run every frame
	 * <p>
	 * Checks mouse for update in inputs
	 */
	public static void update() {
		//continuously update mouse position
		mousePos = GameWorld.getMousePos(); 

	}

	/**
	 * Converts a vector camera view position into a vector map grid position
	 * 
	 * @param mousePos
	 * @return map grid position
	 */
	private static Vector2 convertToGrid(Pair<Integer, Integer> mousePos) {
		float w = GameConfig.getHalfTileWidth();
		float h = GameConfig.getHalfTileHeight();
		Vector2 gridPos = new Vector2();
		// matrix rotation equation see:
		// http://clintbellanger.net/articles/isometric_math/
		
		float x = mousePos.x - MapTile.getTW() * w;		
		float y = mousePos.y - MapTile.getTH() * h;

		gridPos.x = ((x / w) + (y / h)) / 2;
		gridPos.y = ((y / h) - (x / w)) / 2;

		return gridPos;
	}

	/**
	 * returns whether the mouse is over a map tile
	 * 
	 * @return whether the mouse is over a map tile
	 */
	public static boolean tileMouseOver() {

		MapTile tile = getNearestMapTile(); // gets nearest map tile, if not on grid will return first map tile
											// this is cheaper than doing a linear search through all map tiles

		float[] tileV = tile.getVerts();

		return (Intersector.isPointInPolygon(tileV, 0, 8, mousePos.x, mousePos.y));
	}

}
