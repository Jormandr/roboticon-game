package com.jormandr.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.GameStateHandler;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.HumanPlayer;
import com.jormandr.players.Player;

public class GameWorld {

	private static int mapWidth = GameConfig.getMapWidth();
	private static int mapHeight = GameConfig.getMapHeight();
	private static MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	private Player player1;
	private Player player2;
	private Random rand = new Random();
	private GameStateHandler gsh;
	private CollisionHandler collideChecker = new CollisionHandler();
	private InputHandler mouseChecker = new InputHandler();

	public void update(float delta) {
		gameStateMachine();
		mouseChecker.update(); // to joni, this works now

		boolean ball = collideChecker.tileMouseOver();
		Gdx.app.log("update", Boolean.toString(ball));
		// Float.toString(mousePos[1]));

	}

	private void gameStateMachine() {

		switch (gsh.getGameState()) {
		case 0:
			// Player 1 is handling this
			break;
		case 1:
			// Deal with player 1's turn
			gsh.incrementGameState();
			break;
		case 2:
			// Player 2 is handling this
			break;
		case 3:
			// Deal with player 2's turn
			gsh.incrementGameState();
			break;
		case 4:
			produce();
			gsh.incrementGameState();
			break;
		case 5:
			auction();
			gsh.incrementGameState();
			break;
		case 6:
			if (rand.nextInt(GameConfig.getRandomEventChance()) == 1) {
				randomEvent();
			}
			gsh.incrementGameState();
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
		int mapSize = GameConfig.getMapHeight() * GameConfig.getMapWidth();
		for (int i = 0; i < 2; i++) {
			// TODO fix this shit
			Player currentPlayer = i == 0 ? player1 : player2;
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
		Gdx.app.log("GameWorld", "Initialising GSH");
		gsh = new GameStateHandler();
		Gdx.app.log("GameWorld", "Initialising players");
		player1 = new HumanPlayer(0, 0, 0, 0, 0, 0, 1, gsh);
		player2 = new HumanPlayer(0, 0, 0, 0, 0, 0, 2, gsh);

		Gdx.app.log("GameWorld", "Initialising random tiles");
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				mapArray[i][j] = new Plot(i, j, rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1,
						TileType.values()[rand.nextInt(4)], new float[8]);
			}
		}

	}

	public static MapTile[][] getMap() {
		return mapArray;
	}

}
