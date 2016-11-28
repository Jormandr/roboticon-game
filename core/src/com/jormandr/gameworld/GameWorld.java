package com.jormandr.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;

public class GameWorld {

	private int mapWidth = GameConfig.getMapWidth();
	private int mapHeight = GameConfig.getMapHeight();
	private MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	
	public void update(float delta) {

	}

	public GameWorld() {

		Gdx.app.log("GameController", "Initialising random tiles");
		Random rand = new Random();
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				mapArray[i][j] = new Plot(i, j, rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1,
						TileType.values()[rand.nextInt(4)]);
			}
		}
	}
	
	public MapTile[][] getMap() {
		return mapArray;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

}
