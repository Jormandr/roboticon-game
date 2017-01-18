package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * The map tile class is the constructor class for all tiles on the game board
 * 
 * @author Jormandr
 *
 */
public abstract class MapTile {

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
		this.verts[0] = coords.x + 64.0f; // TODO magic numbers
		this.verts[1] = coords.y;
		this.verts[2] = coords.x;
		this.verts[3] = coords.y + 32.0f;
		this.verts[4] = coords.x + 64.0f;
		this.verts[5] = coords.y + 64.0f;
		this.verts[6] = coords.x + 128.0f;
		this.verts[7] = coords.y + 32.0f;

		for (int x = 0; x < 8; x++) {
			Gdx.app.log("MapTile()", "Plot coord" + Integer.toString(x) + " = " + Float.toString(verts[x]));
		}

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
		// TODO magic numbers
		return 640.0f - 64.0f + 64.0f * (getI() - getJ()); // Consider
															// factorising?
	}

	/**
	 * returns y coordinate on screen
	 * 
	 * @return y coordinate on screen
	 */
	public float convertToY() {
		// TODO magic numbers
		return 288.0f - 64.0f + 32.0f * (getI() + getJ());
	}

}
