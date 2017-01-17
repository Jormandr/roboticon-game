package com.jormandr.helpers;

/**
 * GameStateHandler helps control the game state machine
 *
 */
public class GameStateHandler {

	private int gameState = 0;
	private int numberOfStates = GameState.values().length;

	/**
	 * returns the current game state
	 * 
	 * @return the current game state
	 */
	public int getGameState() {
		return gameState;
	}

	/**
	 * sets the current game state
	 * 
	 * @param gameState
	 */
	public void setGameState(int gameState) {
		// TODO throw exception for illegal state
		this.gameState = gameState;
	}

	/**
	 * increments the current game state forwards by one
	 */
	public void incrementGameState() {
		gameState = (gameState + 1) % numberOfStates;
	}

}
