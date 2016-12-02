package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon; // Do not remove yet, will be used
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.InputHandler;

public class CollisionHandler {

	/*
	 * public boolean uiMouseOver(Button ui_element) { // this will check if the
	 * mouse is over a ui element such as a button }
	 */

	// here's the problem, currently 4am so gonna go to sleep for a bit :p
	// in order to only check with the nearest tiles we'd have to look at mouse
	// coords
	// find the nearest point distance between a tile's centre coords and the
	// mouse coords
	// probably worth it tbh, will let you guys implement it later today

	public static MapTile getNearestMapTile() {
		float[] mousePos = InputHandler.getMousePos();
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

	// TODO should these float[2] actually be Vector2's?

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

	/*
	 * These methods are never used (in any useful statement) private float[]
	 * getMouseVerts(float[] mousePos) { float[] mouseVerts = new float[8];
	 * mouseVerts[0] = mousePos[0]; mouseVerts[1] = mousePos[1]; mouseVerts[2] =
	 * mousePos[0] + 1; mouseVerts[3] = mousePos[1]; mouseVerts[4] = mousePos[0]
	 * + 1; mouseVerts[5] = mousePos[1] - 1; mouseVerts[6] = mousePos[0];
	 * mouseVerts[7] = mousePos[1] - 1;
	 * 
	 * return mouseVerts; }
	 * 
	 * // Should this not just be a final float[]? private float[]
	 * getPoly1Verts() { float[] mouseVerts = new float[8]; mouseVerts[0] =
	 * 0.0f; mouseVerts[1] = 0.0f; mouseVerts[2] = 50.0f; mouseVerts[3] = 0.0f;
	 * mouseVerts[4] = 50.0f; mouseVerts[5] = 50.0f; mouseVerts[6] = 0.0f;
	 * mouseVerts[7] = 50.0f;
	 * 
	 * return mouseVerts; }
	 * 
	 * private float[] getPoly2Verts() { float[] mouseVerts = new float[8];
	 * mouseVerts[0] = 60.0f; mouseVerts[1] = 60.0f; mouseVerts[2] = 110.0f;
	 * mouseVerts[3] = 60.0f; mouseVerts[4] = 110.0f; mouseVerts[5] = 110.0f;
	 * mouseVerts[6] = 60.0f; mouseVerts[7] = 110.0f;
	 * 
	 * return mouseVerts; }
	 */

	public static boolean tileMouseOver() {
		// The commented code didn't actually do anything
		// float[] mouseVerts = new float[2]; // Never actually used

		MapTile tile = getNearestMapTile();

		// Gdx.app.log("tileMouseOver", tile.toString());
		float[] mousePos = InputHandler.getMousePos();
		// mouseVerts = convertToGrid(mousePos); // See above
		// float[] mouseV = getMouseVerts(mousePos); // Never actually used
		float[] tileV = tile.getVerts();
		// Polygon tileP = new Polygon(); // Never actually used
		// Polygon mouseP = new Polygon(); // Never actually used
		// mouseP.equals(mouseV); // These are conditions, they do not change
		// anything by executing AFAIK
		// tileP.equals(tileV); // See above

		// float[] poly1V = getPoly1Verts(); // Never actually used
		// float[] poly2V = getPoly2Verts(); // Never actually used
		// Polygon poly1P = new Polygon(); // Never actually used
		// Polygon poly2P = new Polygon(); // Never actually used
		// poly1P.equals(poly1V); // These are conditions, they do not change
		// anything by executing AFAIK
		// poly2P.equals(poly2V); // See above

		return (Intersector.isPointInPolygon(tileV, 0, 8, mousePos[0], mousePos[1]));
		// this function doesn't work

	}
}
