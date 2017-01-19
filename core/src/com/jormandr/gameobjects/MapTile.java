package com.jormandr.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.jormandr.config.GameConfig;
import com.jormandr.helpers.AssetLoader;

/**
 * The map tile class is the constructor class for all tiles on the game board
 * 
 * @author Jormandr
 *
 */
public abstract class MapTile {

	// OK, so we started doing this with hardcoded constants saying "we'll fix
	// it later"
	// It is now later, and I can't remember how we derived those constants
	// though, hence any expression involving h or w is probably wrong \_(シ)_/
	// Don't do magic numbers kids

	private Vector2 position, coords;
	private TileType type;
	private float[] verts;

	/**
	 * @param i
	 * @param j
	 * @param type
	 * @param verts
	 */
	public MapTile(float i, float j, TileType type, float[] verts) {
		position = new Vector2(i, j);
		coords = new Vector2(convertToX(), convertToY());
		this.type = type;
		/**
		 * setting up a polygon of coordinate points for intersection checks
		 * with player inputs such as mouse
		 */
		this.verts = new float[8];
		// TODO check if the 'verts' parameter is essentially pointless due to
		// the above line

		float w = (float) GameConfig.getTileWidth();
		float h = (float) GameConfig.getTileHeight();
		this.verts[0] = coords.x + w;
		this.verts[1] = coords.y;
		this.verts[2] = coords.x;
		this.verts[3] = coords.y + h;
		this.verts[4] = coords.x + w;
		this.verts[5] = coords.y + 2.0f * h;
		this.verts[6] = coords.x + 2.0f * w;
		this.verts[7] = coords.y + 0.5f * h;

		// for (int x = 0; x < 8; x++) {
		// Gdx.app.log("MapTile()", "Plot coord" + Integer.toString(x) + " = " +
		// Float.toString(verts[x]));
		// }

	}

	public void update() {
		// x and y need to be floats in order to use polygons and intersections
		// can use a vector2 to store the position like in the tutorial?
		// x and y can;t be final if we want to move around the map/ we want
		// tiles to move
		// for other reasons e.g. visual juice
	}

	/**
	 * returns x position on grid
	 * 
	 * @return i grid coordinate
	 */
	public float getI() {
		return position.x;
	}

	/**
	 * returns y position on grid
	 * 
	 * @return j grid coordinate
	 */
	public float getJ() {
		return position.y;
	}

	/**
	 * returns tile type
	 * 
	 * @return tile type
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * returns vertices
	 * 
	 * @return vertices
	 */
	public float[] getVerts() {
		return verts;
	}

	/**
	 * returns x coordinate on screen
	 * 
	 * @return x coordinate on screen
	 */
	public float convertToX() {
		float w = (float) GameConfig.getTileWidth();
		return w * (9.0f + getI() - getJ());
	}

	/**
	 * returns y coordinate on screen
	 * 
	 * @return y coordinate on screen
	 */
	public float convertToY() {
		float h = (float) GameConfig.getTileHeight();
		return h * ( 7.0f + getI() + getJ());
	}

}
