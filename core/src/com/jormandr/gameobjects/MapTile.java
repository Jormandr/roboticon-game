package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jormandr.config.GameConfig;
import com.jormandr.helpers.AssetLoader;

/**
 * The map tile class is the constructor class for all tiles on the game board.
 * 
 * <p> Currently only Plots (might extend to landmarks e.g. RCH tile).
 */
public abstract class MapTile {


	// It is now later, and I can't remember how we derived those constants
	// though, hence any expression involving h or w is probably wrong \_(シ)_/
	// Don't do magic numbers kids
	// I WILL FIX IT ARGHGHRGHRG
	
	protected Vector2 position, coords;
	protected TileType type;
	protected float[] verts = new float[8];
	protected float ww,hh;
	protected static float tw, th;

	/**
	 * @param i
	 * @param j
	 * @param type
	 */
	public MapTile(float i, float j, TileType type) {
		position = new Vector2(i, j);

		this.type = type;
		
		float originOffsetX = 2.0f; //halfway across the screen in x axis
		float originOffsetY = 4.0f; //quarter way across the screen in y axis
		
		 ww = (float) GameConfig.getHalfTileWidth();
		 hh = (float) GameConfig.getHalfTileHeight();
		 
		tw = GameConfig.getWidth()/(ww*originOffsetX); //how much to add to move to centre of screen
					
		th = GameConfig.getHeight()/(hh*originOffsetY); //how much to add to move to centre of screen
		
		/*
		  setting up a polygon of coordinate points for intersection checks
		  with player inputs such as mouse
		  
		  in an anticlockwise fashion, builds up a diamond shape, tried drawing some ascii art 
		  but it's pretty difficult :p

		 */
		coords = new Vector2(convertToX(), convertToY());
  		verts[0] = coords.x ;
		verts[1] = coords.y -hh*2;
		verts[2] = coords.x-ww*2;
		verts[3] = coords.y;		
		verts[4] = coords.x;
		verts[5] = coords.y + hh*2;
		verts[6] = coords.x + ww*2;
		verts[7] = coords.y;
	}
	
	
	public static float getTW(){
		return tw;
	}
	
	public static float getTH(){
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
		//matrix rotation equation see: http://clintbellanger.net/articles/isometric_math/
		return (tw+ position.x - getJ())*ww; //add an offset translation value of tw to centre grid in screen
	}

	/**
	 * returns y coordinate on screen 
	 * 
	 * @return y coordinate on screen
	 */
	public float convertToY() {
		//matrix rotation equation see: http://clintbellanger.net/articles/isometric_math/
	
		return(th+getI() + getJ())*hh; //add an offset translation value of th to centre grid in screen
	}

	/**
	 * draw method to be used in GameRenderer
	 * 
	 * @param batcher
	 * @param yOffset
	 */
	public void draw(SpriteBatch batcher, int yOffset) {
		batcher.draw(AssetLoader.textureMap[getType().ordinal()], coords.x, coords.y,-ww,-hh, 62, 34,1,1,0);
	}

}
