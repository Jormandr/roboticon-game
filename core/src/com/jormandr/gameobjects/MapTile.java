package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class MapTile {

	// co-ordinates are top corner of diamond

	// by corner does this mean the transparent space, or the actual corner of
	// the diamond??
	// TODO answer
	private Vector2 position, coords;
	private TileType type;
	private float[] verts;

	public MapTile(float i, float j, TileType type, float[] verts) {
		position = new Vector2(i, j);
		coords = new Vector2(convertToX(), convertToY());
		this.type = type;
		// setting up the polygon coordinates for intersection checks
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
		// TODO magic numbers
		return 640.0f - 64.0f + 64.0f * (getI() - getJ()); // Consider
															// factorising?
	}

	public float convertToY() {
		// TODO magic numbers
		return 288.0f - 64.0f + 32.0f * (getI() + getJ());
	}

}
