package com.jormandr.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class MapTile {

	// co-ordinates are top corner of diamond

	// by corner does this mean the transparent space, or the actual corner of
	// the diamond??
	private Vector2 position, coords;
	private TileType type;
	private static float[] verts;

	public MapTile(float x, float y, TileType type, float[] verts) {
		position = new Vector2(x, y);
		coords = new Vector2(convertToX(), convertToY());
		this.type = type;
		// setting up the polygon coordinates for intersection checks
		verts[0] = coords.x + 64;
		verts[1] = coords.y;
		verts[2] = coords.x;
		verts[3] = coords.y + 32;
		verts[4] = coords.x + 128;
		verts[5] = coords.y + 32;
		verts[6] = coords.x + 64;
		verts[7] = coords.y + 64;
	}

	public void update() {

		// x and y need to be floats in order to use polygons and intersections
		// can use a vector2 to store the position like in the tutorial?
		// x and y can;t be final if we want to move around the map/ we want
		// tiles to move
		// for other reasons e.g. visual juice
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public TileType getType() {
		return type;
	}

	public float[] getVerts() {
		return verts;
	}

	public float convertToX() {
		float convertedX = 0;
		float i = getX();
		float j = getY();

		convertedX = (i - j) * 64;

		return convertedX;
	}

	public float convertToY() {
		float convertedY = 0;
		float i = getX();
		float j = getY();

		convertedY = (i + j) * 32;

		return convertedY;
	}

}
