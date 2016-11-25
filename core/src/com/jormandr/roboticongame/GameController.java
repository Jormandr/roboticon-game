package com.jormandr.roboticongame;

import com.jormandr.gameobjects.MapTile;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.Plot;

public class GameController {
	int mapWidth = GameConfig.getMapWidth();
	int mapHeight = GameConfig.getMapHeight();
	MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];

	public GameController() {
		
		Gdx.app.log("GameController", "Initialising random tiles");
		Random rand = new Random();
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				mapArray[i][j] = new Plot(rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1,
						rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1);
			}
		}
	}
}
