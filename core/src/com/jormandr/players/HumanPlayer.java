package com.jormandr.players;

import com.jormandr.helpers.GameStateHandler;

public class HumanPlayer extends Player {

	public HumanPlayer(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber,
			GameStateHandler gsh) {
		super(score, ore, food, energy, money, roboticonsOwned, playerNumber, gsh);
	}
}
