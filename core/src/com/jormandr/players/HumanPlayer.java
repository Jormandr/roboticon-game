package com.jormandr.players;

import com.jormandr.helpers.GameStateHandler;

public class HumanPlayer extends Player {

	public HumanPlayer(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber,
			GameStateHandler gsh) {
		super(score, ore, food, energy, money, roboticonsOwned, playerNumber, gsh);
	}

	public int onClick() {
		// handle what happens with a player if the mouse is clicked
		// check to see if there is a plot at the coordinate which was clicked
		// or check to see if a ui element was at the coordinate which was
		// clicked
		return 0;
	}

	// Should these be public too?
	private void buyPlots() {
		// check if clicked on plot
	}

	private void buyRoboticons() {
		// check if bought roboticons
	}

	private void customiseRoboticons() {
		// check if clicked on a plot
	}

}
