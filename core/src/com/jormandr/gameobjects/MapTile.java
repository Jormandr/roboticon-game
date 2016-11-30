package com.jormandr.gameobjects;

public abstract class MapTile {

	// co-ordinates are top corner of diamond
	private final int x, y;
	private TileType type;

	public MapTile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TileType getType() {
		return type;
	}

}
