package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
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

	public MapTile getNearestMapTile() {
		float[] mousePos = InputHandler.getMousePos();
		float[] gridPos = convertToGrid(mousePos);

		MapTile[][] map = GameWorld.getMap();

		MapTile tile = map[0][0];
		if ((gridPos[0] < 0) || (gridPos[0] > map.length - 1) || (gridPos[1] < 0) || (gridPos[1] > map[1].length - 1)) {

		}

		else {
			tile = map[(int) gridPos[0]][(int) gridPos[1]];
		}

		return map[0][0];

	}

	private float[] convertToGrid(float[] position) {

		float[] gridPos = new float[2];
		float x = position[0];
		float y = position[1];

		gridPos[0] = (x / 64 + y / 32) / 2;
		gridPos[1] = (y / 32 - (x / 64)) / 2;

		return gridPos;
	}

	private float[] getMouseVerts(float[] mousePos) {
		float[] mouseVerts = new float[8];
		mouseVerts[0] = mousePos[0];
		mouseVerts[1] = mousePos[1];
		mouseVerts[2] = mousePos[0] + 1;
		mouseVerts[3] = mousePos[1] + 1;
		mouseVerts[4] = mousePos[0] + 1;
		mouseVerts[5] = mousePos[1] + 1;
		mouseVerts[6] = mousePos[0] + 2;
		mouseVerts[7] = mousePos[1] + 2;

		return mouseVerts;
	}

	public boolean tileMouseOver() {

		float[] mouseVerts = new float[2];

		MapTile tile = getNearestMapTile();

		float[] mousePos = InputHandler.getMousePos();
		mouseVerts = convertToGrid(mousePos);
		float[] mouseV = getMouseVerts(mousePos);
		float[] tileV = tile.getVerts();
		Polygon tileP = new Polygon();
		Polygon mouseP = new Polygon();
		mouseP.equals(mouseV);
		tileP.equals(tileV);
		return (Intersector.overlapConvexPolygons(mouseP, tileP, null));

	}
}
