package com.jormandr.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import com.jormandr.players.HumanPlayer;
import com.jormandr.players.Player;

public class GameWorld {

	private int mapWidth = GameConfig.getMapWidth();
	private int mapHeight = GameConfig.getMapHeight();
	private MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	private int state = 1;
	private final int numberOfPlayers = GameConfig.getPlayerNumbers();
	private Player[] allPlayers = new Player[numberOfPlayers];
	private Random rand = new Random();

	public void update(float delta) {
		switch (state) {
		case 1:
			for (int i = 0; i < numberOfPlayers; i++) {
				allPlayers[i].makeTurn();
			}
			state++;
			break;
		case 2:
			produce();
			state++;
			break;
		case 3:
			auction();
			state++;
			break;
		case 4:
			if (rand.nextInt(GameConfig.getRandomEventChance()) == 1) {
				randomEvent();
			}
			state = 1;
			break;
		}

	}

	private void randomEvent() {
		// TODO Auto-generated method stub
		
	}

	private void auction() {
		// TODO Auto-generated method stub
		
	}

	private void produce() {
		Plot[] playersPlots;
		int mapSize = GameConfig.getMapHeight()*GameConfig.getMapWidth();
		for (int i = 0; i < numberOfPlayers; i++) {
			Player currentPlayer = allPlayers[i];
			playersPlots = currentPlayer.getPlotsOwned();
			for (int j = 0; j < mapSize; j++) {
				Plot currentPlot = playersPlots[j];
				if (currentPlot != null) {
					currentPlayer.changeEnergy(currentPlot.getEnergyValue());
					currentPlayer.changeOre(currentPlot.getOreValue());
				}
			}
		}
		
	}

	public GameWorld() {

		Gdx.app.log("GameWorld", "Initialising players");
		allPlayers[0] = new HumanPlayer(0, 0, 0, 0, 0, 0);
		allPlayers[1] = new HumanPlayer(0, 0, 0, 0, 0, 0);

		Gdx.app.log("GameWorld", "Initialising random tiles");
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

}
