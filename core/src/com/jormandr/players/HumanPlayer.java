package com.jormandr.players;

import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.GameStateHandler;

/**
 * HumanPlayer is a child of Player which can be controlled by humans to play
 * the game
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber,
			GameStateHandler gsh, int playerState) {
		super(score, ore, food, energy, money, roboticonsOwned, playerNumber, gsh, playerState);
	}
	
	@Override
	public void playerStateMachine() {
		switch (playerState) {
		case 0:
			// buy plots of land
			break;
		case 1:
			// buying roboticons and gambling
			// timed state

			break;
		case 2:
			// customising and placing roboticons
			// timed state
			break;
		case 3:
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
	
	 */
	/*
	private void buyPlots() {
		// check if clicked on plot
		if (CollisionHandler.mouseDown() && CollisionHandler.tileMouseOver()){
				MapTile tile = CollisionHandler.getNearestMapTile();
				if (tile instanceof Plot) {
					
				}
			
		}
	}
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
