package com.jormandr.gameobjects;

public abstract class MapTile {

	// co-ordinates are top corner of diamond
	private final int x, y;

	public MapTile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
