package com.jormandr.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.GameStateHandler;
import com.jormandr.players.HumanPlayer;
import com.jormandr.players.Player;

/**
 * The gameworld controls the main overall logic of the game, primarily the
 * state machine of which each state in the brief is fulfilled
 *
 */
public class GameWorld {

	private static int mapWidth = GameConfig.getMapWidth();
	private static int mapHeight = GameConfig.getMapHeight();
	private static MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	private Player player1;
	private Player player2;
	private Random rand = new Random();
	private GameStateHandler gsh;
	private CollisionHandler collideChecker = new CollisionHandler();

	/**
	 * Update is run every frame, updates of all game objects put in here so
	 * their update logic is also checked every frame
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		gameStateMachine();
		collideChecker.update();

		boolean ball = collideChecker.tileMouseOver();
		// Gdx.app.log("update", Boolean.toString(ball));

	}

	/**
	 * The state machine which shows the cycle of each round
	 * <p>
	 */
	private void gameStateMachine() {

		switch (gsh.getGameState()) {
		case 0:
			// Player 1 is handling this
			player1.playerStateMachine();
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

	/**
	 * The logic for random event state
	 */
	private void randomEvent() {
		// TODO Auto-generated method stub

	}

	/**
	 * The logic for the auction state
	 * 
	 */
	private void auction() {
		// TODO Auto-generated method stub

	}

	/**
	 * The logic for the production state
	 */
	private void produce() {
		Plot[] playersPlots;
		int mapSize = GameConfig.getMapHeight() * GameConfig.getMapWidth();
		for (int i = 0; i < 2; i++) {
			// Consider making this less 'hacky'
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

	/**
	 * The constructor for Gameworld
	 * <p>
	 * Initialises GameStateHandler, Players and plot tiles.
	 */
	public GameWorld() {
		Gdx.app.log("GameWorld", "Initialising GSH");
		gsh = new GameStateHandler();
		Gdx.app.log("GameWorld", "Initialising players");
		player1 = new HumanPlayer(0, 0, 0, 0, 0, 0, 1, gsh);
		player2 = new HumanPlayer(0, 0, 0, 0, 0, 0, 2, gsh);

		Gdx.app.log("GameWorld", "Initialising random tiles");
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				Plot Newplot = new Plot(i, j, rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1,
						TileType.values()[rand.nextInt(4)], new float[8]);
				mapArray[i][j] = Newplot;
			}
		}

	}

	/**
	 * returns the array of map tiles
	 * 
	 * @return the array of map tiles
	 */
	public static MapTile[][] getMap() {
		return mapArray;
	}

}
