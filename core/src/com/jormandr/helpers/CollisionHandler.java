package com.jormandr.helpers;

import com.badlogic.gdx.math.Intersector;
import com.jormandr.gameobjects.MapTile;
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

	public boolean tileMouseOver(MapTile tile) {

		if (Intersector.overlapConvexPolygons(tile.getVerts(), InputHandler.getMousePos(), null))
			return true;

		else
			return false;
	}
}
