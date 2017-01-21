package com.jormandr.players;

/**
 * HumanPlayer is a child of Player which can be controlled by humans to play
 * the game
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber,
			int playerState) {
		super(score, ore, food, energy, money, roboticonsOwned, playerNumber, playerState);
	}

	@Override
	public void playerStateMachine() {
		switch (playerState) {
		case PLOT:
			//buy plots
			break;
		case BUY:
			// buying roboticons and gambling
			// timed state

			break;
		case PLACE:
			// customising and placing roboticons
			// timed state
			break;
		case END:
			// final state (updates gamestate Machine)
			break;
		}

	}

	/**
	 * returns whether mouse is clicked
	 * 
	 * @return whether mouse is clicked
	 */

	/**
	 * buyPlots logic
	 * 
	 */
	/*
	 * private void buyPlots() { // check if clicked on plot if
	 * (CollisionHandler.mouseDown() && CollisionHandler.tileMouseOver()){
	 * MapTile tile = CollisionHandler.getNearestMapTile(); if (tile instanceof
	 * Plot) {
	 * 
	 * }
	 * 
	 * } }
	 */
	/**
	 * buyRoboticons logics
	 */
	private void buyRoboticons() {
		// check if bought roboticons
	}

	/**
	 * customiseRoboticons logic
	 */
	private void customiseRoboticons() {
		// check if clicked on a plot
	}

}
