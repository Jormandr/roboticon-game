package com.jormandr.helpers;

public class GameStateHandler {

	private int gameState = 1;
	private int numberOfStates = 7;

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		// TODO throw exception for illegal state
		this.gameState = gameState;
	}

	public void incrementGameState() {
		gameState = (gameState + 1) % numberOfStates;
	}

}