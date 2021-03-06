package com.jormandr.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jormandr.config.GameConfig;
import com.jormandr.helpers.AssetLoader;

/**
 * The map tile class is the constructor class for all tiles on the game board.
 * 
 * <p>
 * Currently only Plots (might extend to landmarks e.g. RCH tile).
 */
public abstract class MapTile {


	protected Vector2 position, coords;
	protected TileType type;
	protected float[] verts = new float[8];
	private final static float originOffsetX = 2.0f;
	private final static float originOffsetY = 4.0f;
	protected static float ww =  (float) GameConfig.getHalfTileWidth();
	protected static float hh = (float) GameConfig.getHalfTileHeight();;
	protected static float tw = GameConfig.getWidth() / (ww * originOffsetX);
	protected static float th = GameConfig.getHeight() / (hh * originOffsetY);

	/**
	 * @param i
	 * @param j
	 * @param type
	 */
	public MapTile(float i, float j, TileType type) {
		position = new Vector2(i, j);

		this.type = type;

		/*
		 * setting up a polygon of coordinate points for intersection checks
		 * with player inputs such as mouse
		 * 
		 * builds up a diamond shape, tried drawing
		 * some ascii art but it's pretty difficult :p
		 * 
		 * 
		 * the first point pair (verts[0],verts[1]) is the top corner of the diamond
		 * the origin is the centre of the diamond, the points are calculated relative to this origin
		 * 
		 */
		coords = new Vector2(convertToX(), convertToY());
		verts[0] = coords.x;
		verts[1] = coords.y - hh * 2;
		verts[2] = coords.x - ww * 2;
		verts[3] = coords.y;
		verts[4] = coords.x;
		verts[5] = coords.y + hh * 2;
		verts[6] = coords.x + ww * 2;
		verts[7] = coords.y;
	}

	/**
	 * returns tile map width offset
	 * @return tw
	 */
	public static float getTW() {
		return tw;
	}

	/**
	 * returns tile map height offset
	 * @return tw
	 */
	public static float getTH() {
		return th;
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
		// matrix rotation equation see:
		// http://clintbellanger.net/articles/isometric_math/
		return (tw + position.x - position.y) * ww; // add an offset translation
												// value of tw to centre grid in
												// screen
	}

	/**
	 * returns y coordinate on screen
	 * 
	 * @return y coordinate on screen
	 */
	public float convertToY() {
		// matrix rotation equation see:
		// http://clintbellanger.net/articles/isometric_math/

		return (th + position.x + position.y) * hh; // add an offset translation value
											// of th to centre grid in screen
	}

	/**
	 * draw method to be used in GameRenderer
	 * 
	 * @param batcher
	 * @param yOffset
	 */
	public void draw(SpriteBatch batcher, int yOffset) {
		//offset from origin left by half tile width , and up by half tile height as sprite origin is in top right corner
		// whereas tile origin is centre of tile
		batcher.draw(AssetLoader.textureMap[getType().ordinal()], coords.x, coords.y, -ww, -hh, 62, 34, 1, 1, 0);
	}

}
