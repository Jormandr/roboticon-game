package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class MapTile {

	// co-ordinates are top corner of diamond

	// by corner does this mean the transparent space, or the actual corner of
	// the diamond??
	private Vector2 position, coords;
	private TileType type;
	private float[] verts;

	public MapTile(float i, float j, TileType type, float[] verts) {
		position = new Vector2(i, j);
		coords = new Vector2(convertToX(), convertToY());
		this.type = type;
		// setting up the polygon coordinates for intersection checks
		this. verts = new float[8];
		this. verts[0] = coords.x + 64.0f;
		this. verts[1] = coords.y;
		this. verts[2] = coords.x;
		this. verts[3] = coords.y + 32.0f;
		this. verts[4] = coords.x + 64.0f;
		this. verts[5] = coords.y + 64.0f;
		this. verts[6] = coords.x + 128.0f;
		this. verts[7] = coords.y + 32.0f;
		
		Gdx.app.log("Plot Coord:", Float.toString(verts[0]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[1]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[2]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[3]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[4]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[5]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[6]));
		Gdx.app.log("Plot Coord:", Float.toString(verts[7]));
	}

	public void update() {

		// x and y need to be floats in order to use polygons and intersections
		// can use a vector2 to store the position like in the tutorial?
		// x and y can;t be final if we want to move around the map/ we want
		// tiles to move
		// for other reasons e.g. visual juice
	}

	public float getI() {
		return position.x;
	}

	public float getJ() {
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
		float i = getI();
		float j = getJ();

		convertedX = 640.0f - 64.0f + (i - j) * 64.0f ;

		return convertedX;
	}

	public float convertToY() {
		float convertedY = 0;
		float i = getI();
		float j = getJ();

		convertedY = 288.0f -64 + (i + j) * 32.0f;

		return convertedY;
	}

}
