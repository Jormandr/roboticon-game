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
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;

/**
 * The gameworld controls the main overall logic of the game, primarily the
 * state machine of which each state in the brief is fulfilled
 *
 */
public class GameWorld {
	
	public enum WorldState {
		RUNNING, MENU, START, END;
	}

	private WorldState currentState;
	private static int mapWidth = GameConfig.getMapWidth();
	private static int mapHeight = GameConfig.getMapHeight();
	private static MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	private static int[] mousePos = new int[2];
	private Player player1;
	private Player player2;
	private Random rand = new Random();
	private GameStateHandler gsh;
	
	
	/**
	 * The constructor for Gameworld
	 * <p>
	 * Initialises GameStateHandler, Players and plot tiles.
	 */
	public GameWorld() {
		currentState = WorldState.RUNNING;
		Gdx.app.log("GameWorld", "Initialising GSH");
		gsh = new GameStateHandler();
		Gdx.app.log("GameWorld", "Initialising players");
		player1 = new HumanPlayer(0, 0, 0, 0, 0, 0, 1, gsh, 0);
		player2 = new HumanPlayer(0, 0, 0, 0, 0, 0, 2, gsh, 0);

		Gdx.app.log("GameWorld", "Initialising random tiles");
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				Plot Newplot = new Plot(i, j, rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(50) + 1,
						TileType.values()[rand.nextInt(4)]);
				mapArray[i][j] = Newplot;
			}
		}

	}

	/**
	 * Update is run every frame, updates of all game objects put in here so
	 * their update logic is also checked every frame
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		
		worldStateMachine(delta);
		gameStateMachine();
		
		mousePos[0] = Gdx.input.getX();
		mousePos[1] = Gdx.input.getY();

	}
	
	private void worldStateMachine(float delta){
		
        switch (currentState) {
        case RUNNING:
            updateRunning(delta);
            break;

        case MENU:
        	updateMenu(delta);
        	break;
        case START:
        case END:
        default:
            updateRunning(delta);
            break;
        }
	}
	
	private void updateRunning(float delta){
        if (delta > .15f) {
            delta = .15f;
        }
        
		CollisionHandler.update();
		
		/*if() if we click on a tile or market buttton, or pause button
		to bring up a menu then go to MENU world state*/
	}
	
	private void updateMenu(float delta){
        if (delta > .15f) {
            delta = .15f;
			Gdx.app.log("GameWorld: ", "InMenu");
        }
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
			player2.playerStateMachine();
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
	 * returns the array of map tiles
	 * 
	 * @return the array of map tiles
	 */
	public static MapTile[][] getMap() {
		return mapArray;
	}

	/**
	 * returns the current game state
	 * 
	 * @return the current game state
	 */
	public int getGameState() {
		return gsh.getGameState();
	}

	public static int[] getMousePos() {
		return mousePos;
	}
	
	public boolean isMenu(){
		return currentState == WorldState.MENU;
	}
	
	public boolean isRunning(){
		return currentState == WorldState.RUNNING;
	}
	
	public void toMenu(){
		currentState = WorldState.MENU;
		// then figure out what menu we need to bring up
	}

}
