package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
// import com.badlogic.gdx.math.Polygon; // Do not remove yet, will be used // Yeah but the warning was annoying me, commented
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameworld.GameWorld;
/**
 * CollisionHandler handles collision logic of game objects and assets
 *
 */
public class CollisionHandler {

	/**
	 * returns the nearest map tile to the mouse
	 * <p>
	 * if mouse is off map grid it will return the first tile in the map array
	 * 
	 * @return returns the nearest map tile to the mouse
	 */
	static boolean buffer;
	public CollisionHandler(){
	buffer=true;	
	}
	public static MapTile getNearestMapTile() {
		float[] mousePos = getMousePos();
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

	private static float[] mousePos = new float[2];

	/**
	 * Update is run every frame
	 * <p>
	 * Checks mouse for update in inputs
	 */
	public static void update() {
		mousePos[0] = Gdx.input.getX();
		// Gdx.app.log("Input Handler", Float.toString(mousePos[0]));
		mousePos[1] = Gdx.input.getY();
		// Gdx.app.log("Input Handler", Float.toString(mousePos[1]));
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&buffer) {
			Gdx.app.log("Input Handler", Float.toString(mousePos[1]));
			buffer=false;
		}
		else if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)==false&&buffer==false)
		{
			buffer=true;
		}
	}

	/**
	 * returns the position of the mouse in camera view
	 * 
	 * @return the position of the mouse in camera view
	 */
	public static float[] getMousePos() {
		return mousePos;
	}

	// TODO should these float[2] actually be Vector2's?

	/**
	 * Converts a vector camera view position into a vector map grid position
	 * 
	 * @param position
	 * @return map grid position
	 */
	private static float[] convertToGrid(float[] position) {

		float[] gridPos = new float[2];
		float x = position[0] - (640);
		float y = position[1] - (288 - 64);
		// the reason this is shit is because the rendering of the tiles is
		// offset to be centred in the screen but the actual position is at 0,0
		// screen position
		gridPos[0] = ((x / 64) + (y / 32)) / 2;
		gridPos[1] = ((y / 32) - (x / 64)) / 2;

		return gridPos;
	}

	/**
	 * returns whether the mouse is over a map tile
	 * 
	 * @return whether the mouse is over a map tile
	 */
	public static boolean tileMouseOver() {

		MapTile tile = getNearestMapTile();

		// Gdx.app.log("tileMouseOver", tile.toString());
		float[] mousePos = getMousePos();

		float[] tileV = tile.getVerts();

		return (Intersector.isPointInPolygon(tileV, 0, 8, mousePos[0], mousePos[1]));

	}
}
