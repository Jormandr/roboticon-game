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
import com.jormandr.helpers.InputHandler.MenuUI;
import com.jormandr.helpers.UIHandler;
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
		RUNNING, MENU;
	}

	public enum GameState {
		START, RANDOMEVENT, WAITINGFORP1, HANDLINGP1, WAITINGFORP2, HANDLINGP2, PRODUCE, AUCTIONP1, AUCTIONP2, ENDCHECK, END;
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
	 * Update is run every frame, updates of both GameWorld state machines put
	 * here so they run continuously.
	 * 
	 * @param delta
	 */
	public void update() {

		worldStateMachine();
		gameStateMachine();

		mousePos.x = Gdx.input.getX();
		mousePos.y = Gdx.input.getY();

	}

	/**
	 * This worldStateMachine allows you to know what main state you are in
	 * e.g. menu or main game logic
	 */
	private void worldStateMachine() {

		switch (currentState) {
		case RUNNING:
			updateRunning();
			break;
		case MENU:
			updateMenu();
			break;
		default:
			updateRunning();
			break;
		}
	}

	/**
	 * the block code to continuously  to run whilst in the WorldState.RUNNING state
	 */
	private void updateRunning() {
		CollisionHandler.update();
	}

	/**
	 * the block code to continuously  to run whilst in the WorldState.MENU state
	 */
	private void updateMenu() {
		// blank for now
	}

	/**
	 * The state machine which shows the cycle of each round
	 * <p>
	 * user manual can help to break this down
	 */
	private void gameStateMachine() {

		switch (gameState) {
		case START:
			break;
		case RANDOMEVENT:
			//this is the random event state where the random event can occur
			if (rand.nextInt(GameConfig.getRandomEventChance()) == 1) {
				randomEvent();
			} else {
				setGameState(GameState.WAITINGFORP1);
			}
			break;
		case WAITINGFORP1:
			// This sets up player 1 state machine to play out their turn
			player1.setState(PlayerState.PLOT);
			setGameState(GameState.HANDLINGP1);
			break;
		case HANDLINGP1:
			// Deal with player 1's turn
			player1.playerStateMachine();
			break;
		case WAITINGFORP2:
			// This sets up player 2 state machine to play out their turn
			player2.setState(PlayerState.PLOT);
			setGameState(GameState.HANDLINGP2);
			break;
		case HANDLINGP2:
			// Deal with player 2's turn
			player2.playerStateMachine();
			break;
		case PRODUCE:
			//this is when production is run, currently no graphics
			produce();
			player1.updateScore();
			player2.updateScore();
			break;
		case AUCTIONP1:
			player2.updateScore();
			//this is when player 1 can access the auction panel
			
			// because this auction() code block is run, when you need to implement trades between the players,
			//switch between AUCTIONP1 and AUCTIONP2 to send bids/trade deals to the other player.
			
			auction();
			break;
		case AUCTIONP2:
			//this is when player 2 can access the auction panel
			auction();
			player1.updateScore();
			break;
		case ENDCHECK:
			//checks to see if win condition has been met
			player2.updateScore();
			Market.update();
			if (endCheck()) {
				toMenuEnd();
				setGameState(GameState.END);
				break;
			}
			//loop back to random event if not
			setGameState(GameState.RANDOMEVENT);
			break;
		case END:
			//end of game dealt with in this state
			break;
		}
	}

	/**
	 * The logic for random event state
	 */

	private void randomEvent() {
		// random event stuff yet to be implemented here

		// at end of random event
		setGameState(GameState.WAITINGFORP1);

	}

	/**
	 * The logic for the auction state
	 * 
	 */
	private void auction() {
		// player 1's auction
		// player 2's auction
		if (currentState != WorldState.MENU) {
			toMenuMarket();
		}
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

	/**
	 * in the method, state machines (both GameWorld and Player) handled
	 * to reach correct phase of plot menu, correct buttons loaded from InputHandler
	 */
	public void toMenuPlot() {
		currentState = WorldState.MENU;
		InputHandler.setMenu(MenuUI.PLOT);
		// run a method that creates all the menu buttons
		// so 4 possible menu's that we can go to, or is it 5?
		UIHandler.clearMenuButtons();
		Player currentPlayer = getPlayer(gameState);
		if (currentPlayer != null) {
			switch (currentPlayer.getState()) {
			case PLOT:
				UIHandler.LoadPlotPlotMenu();
				break;
			case BUY:
			case END:
				UIHandler.LoadPlotBuyMenu();
				break;
			case PLACE:
				UIHandler.LoadPlotPlaceMenu();
				break;
			}
		}
	}

	/**
	 * in the method, state machines (both GameWorld and Player) handled
	 * to reach correct phase of market menu, correct buttons loaded from InputHandler
	 */
	public void toMenuMarket() {
		currentState = WorldState.MENU;
		InputHandler.setMenu(MenuUI.MARKET);
		// run a method that creates all the menu buttons
		// so 4 possible menu's that we can go to, or is it 5?
		UIHandler.clearMenuButtons();
		Player currentPlayer = getPlayer(gameState);
		if (currentPlayer != null) {
			switch (currentPlayer.getState()) {
			case PLOT:
			case PLACE:
				UIHandler.LoadMarketVoidMenu();
				break;
			case BUY:
				UIHandler.LoadMarketRoboMenu();
				break;
			case END:
				UIHandler.LoadMarketAuctionMenu();
				break;
			}
		}
	}

	/**
	 * in the method, state machines (both GameWorld and Player) handled
	 * to reach end menu, correct buttons loaded from InputHandler
	 */
	private void toMenuEnd() {
		InputHandler.setMenu(MenuUI.END);
		currentState = WorldState.MENU;
		UIHandler.clearMenuButtons();
		UIHandler.LoadEndMenu();
	}

	/**
	 * in the method, state machines (both GameWorld and Player) handled
	 * to reach end menu, correct buttons loaded from InputHandler
	 */
	@SuppressWarnings("unused")
	private void toMenuStart() {
		currentState = WorldState.MENU;
		UIHandler.clearMenuButtons();
		// currently start menu not implemented so never used to get to a non-existent menu
	}

	/**
	 * Sets the game running
	 */
	public void toRunning() {
		UIHandler.clearMenuButtons();
		UIHandler.LoadGameMenu();
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

	/**
	 * updates the timer each frame 
	 * @param seconds
	 */
	public static void updateTimer(int seconds) {
		timer -= (Gdx.graphics.getDeltaTime());
		if (timer <= 0) {
			getPlayer(gameState).nextState();
			timer = seconds;
		}
	}

	/**
	 * sets the start amount of time you want to count down from
	 * @param seconds
	 */
	public static void setTimer(int seconds) {
		timer = initTimer = (float) seconds ;
	}

	/**
	 * returns the current time on the timer
	 * @return timer
	 */
	public static int getTimer() {
		return (int) timer ;
	}

	/**
	 * returns the proportion of time remaining in timer
	 * @return timer/initTimer
	 */
	public static float getTimerPercentage() {
		return timer / initTimer;
	}

	/**
	 * returns whether end game condition is set
	 * <p>
	 * checks whether all tiles are owned
	 * @return  whether all tiles are owned
	 */
	private boolean endCheck() {
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();
		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				MapTile tile = mapArray[i][j];
				if (tile instanceof Plot) {
					if (((Plot) tile).getOwned() == null) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * calculates which player has the higher score 
	 * @return player who has highest score
	 */
	public Player getWinner() {
		if (player1.getScore() > player2.getScore()) {
			return player1;
		} else if (player1.getScore() == player2.getScore()) {
			return null;
		}
		return player2;
	}

}
