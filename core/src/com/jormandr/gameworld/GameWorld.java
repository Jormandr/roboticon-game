package com.jormandr.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.HumanPlayer;
import com.jormandr.players.Player;
import com.jormandr.players.Player.PlayerState;
import com.jormandr.misctypes.Pair;

/**
 * The gameworld controls the main overall logic of the game, primarily the
 * state machine of which each state in the brief is fulfilled
 *
 */
public class GameWorld {

	public enum WorldState {
		RUNNING, MENU, START, END;
	}

	public enum GameState {
		 RANDOMEVENT, WAITINGFORP1, HANDLINGP1, WAITINGFORP2, HANDLINGP2, PRODUCE, AUCTIONP1,AUCTIONP2;
	}

	// TODO should more of this be static?
	private WorldState currentState;
	private static GameState gameState;
	private static int mapWidth = GameConfig.getMapWidth();
	private static int mapHeight = GameConfig.getMapHeight();
	private static MapTile[][] mapArray = new MapTile[mapWidth][mapHeight];
	private static Pair<Integer, Integer> mousePos = new Pair<Integer, Integer>(0, 0);
	private static Player player1;
	private static Player player2;
	private static Market market;
	private static float timer, initTimer = 30.0f;
	private Random rand = new Random();

	/**
	 * The constructor for Gameworld
	 * <p>
	 * Initialises GameStateHandler, Players and plot tiles.
	 */
	public GameWorld() {
		currentState = WorldState.RUNNING;
		gameState = GameState.WAITINGFORP1;
		// Here we realistically emulate socioeconomic issues such as
		// inheritance
		// disparity
		player1 = new HumanPlayer(0, 0, 0, 0, 100, 2, 1);
		player2 = new HumanPlayer(0, 0, 0, 0, 10, 0, 2);
		market = new Market(100,100,100,10,10.0f,10.0f,10.0f,10.0f);
		timer = initTimer = 30.0f;

		int oreMaxValue = GameConfig.getOreValueRandomLimit();
		int foodMaxValue = GameConfig.getFoodValueRandomLimit();
		int energyMaxValue = GameConfig.getEnergyValueRandomLimit();

		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				Plot Newplot = new Plot(i, j, rand.nextInt(oreMaxValue) + 1, rand.nextInt(foodMaxValue) + 1,
						rand.nextInt(energyMaxValue) + 1, TileType.values()[rand.nextInt(TileType.values().length)]);
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
	public void update() {

		worldStateMachine();
		gameStateMachine();

		mousePos.x = Gdx.input.getX();
		mousePos.y = Gdx.input.getY();

	}

	private void worldStateMachine() {

		switch (currentState) {
		case RUNNING:
			updateRunning();
			break;
		case MENU:
			updateMenu();
			break;
		case START:
			break;
		case END:
			break;
		default:
			updateRunning();
			break;
		}
	}

	private void updateRunning() {
		CollisionHandler.update();
	}

	private void updateMenu() {

	}

	/**
	 * The state machine which shows the cycle of each round
	 * <p>
	 */
	private void gameStateMachine() {

		switch (gameState) {
		case RANDOMEVENT:
			player2.updateScore();
			market.update();
			if (rand.nextInt(GameConfig.getRandomEventChance()) == 1) {
				randomEvent();
			}
			break;
		case WAITINGFORP1:
			// Player 1 is handling this
			player1.setState(PlayerState.PLOT);
			setGameState(GameState.HANDLINGP1);
			break;
		case HANDLINGP1:
			// Deal with player 1's turn
			player1.playerStateMachine();
			break;
		case WAITINGFORP2:
			// Player 2 is handling this
			player2.setState(PlayerState.PLOT);
			setGameState(GameState.HANDLINGP2);
			break;
		case HANDLINGP2:
			// Deal with player 2's turn
			player2.playerStateMachine();
			break;
		case PRODUCE:
			produce();
			player1.updateScore();
			player2.updateScore();
			break;
		case AUCTIONP1:
			auction();
			break;
		case AUCTIONP2:
			auction();
			player1.updateScore();
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
		//player 1's auction
		//player 2's auction
		toMenuMarket();
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
					currentPlayer.changeFood(currentPlot.getFoodValue());
				}
			}
		setGameState(GameState.AUCTIONP1);
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
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * returns the mouse position as a pair of integers
	 * 
	 * @return the mouse position
	 */
	public static Pair<Integer, Integer> getMousePos() {
		return mousePos;
	}

	/**
	 * returns true if the game is in the menu
	 * 
	 * @return true if the game is in the menu
	 */
	public boolean isMenu() {
		return currentState == WorldState.MENU;
	}

	/**
	 * returns true if the game is running
	 * 
	 * @return true if the game is running
	 */
	public boolean isRunning() {
		return currentState == WorldState.RUNNING;
	}

	@SuppressWarnings("incomplete-switch")
	public void toMenuPlot() {
		currentState = WorldState.MENU;
		// run a method that creates all the menu buttons
		// so 4 possible menu's that we can go to, or is it 5?
		InputHandler.clearMenuButtons();
		Player currentPlayer = getPlayer(gameState);
		if (currentPlayer != null) {
			switch (currentPlayer.getState()) {
			case PLOT:
				InputHandler.LoadPlotPlotMenu();
				break;
			case BUY:
			case END:
				InputHandler.LoadPlotBuyMenu();
				break;
			case PLACE:
				InputHandler.LoadPlotPlaceMenu();
				break;
			}
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public void toMenuMarket() {
		currentState = WorldState.MENU;
		// run a method that creates all the menu buttons
		// so 4 possible menu's that we can go to, or is it 5?
		InputHandler.clearMenuButtons();
		Player currentPlayer = getPlayer(gameState);
		if (currentPlayer != null) {
			switch (currentPlayer.getState()) {
			case PLOT:
			case PLACE:
				InputHandler.LoadMarketVoidMenu();
				break;
			case BUY:
				InputHandler.LoadMarketRoboMenu();
				break;
			case END:
				InputHandler.LoadMarketAuctionMenu();
				break;
			}
		}
	}

	/**
	 * Sets the game running
	 */
	public void toRunning() {
		InputHandler.clearMenuButtons();
		InputHandler.LoadGameMenu();
		currentState = WorldState.RUNNING;
	}

	/**
	 * Sets the game state
	 * 
	 * @param state
	 */
	public void setGameState(GameState state) {
		gameState = state;
	}

	/**
	 * Currently returns the player iff the game is in a "handling" state, null
	 * otherwise This is probably not intended behaviour
	 * 
	 * @param state
	 * @return the player or null
	 */
	public static Player getPlayer(GameState state) {
		switch (state) {
		case HANDLINGP1:
		case AUCTIONP1:
			return player1;
		case HANDLINGP2:
		case AUCTIONP2:
			return player2;
		default:
			return null;
		}
	}

	/**
	 * returns the current world state
	 * 
	 * @return
	 */
	public WorldState getWorldState() {
		return currentState;
	}

	/**
	 * Increments the game state
	 */
	public static void nextGameState() {
		gameState = GameState.values()[(gameState.ordinal() + 1) % GameState.values().length];
	}

	public Market getMarket(){
		return market;
	}
	
	public static void updateTimer(int seconds){
		timer -= (1+Gdx.graphics.getDeltaTime());
		if(timer <= 0){
			getPlayer(gameState).nextState();
			timer = seconds*60;	
		}
	}
	
	public static void setTimer(int seconds){
		timer = initTimer = (float) seconds*60;
	}
	
	public static int getTimer(){
		return (int) timer/60;
	}
	
	public static float getTimerPercentage(){
		return timer/initTimer;
	}
}
