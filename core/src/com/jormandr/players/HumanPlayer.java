package com.jormandr.players;

import com.jormandr.gameworld.GameWorld;

/**
 * HumanPlayer is a child of Player which can be controlled by humans to play
 * the game
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber) {
		super(score, ore, food, energy, money, roboticonsOwned, playerNumber);
	}

	@Override
	public void playerStateMachine() {
		switch (playerState) {
		case PLOT:
			// buy plots
			GameWorld.setTimer(30);
			break;
		case BUY:
			//buy roboticons
			GameWorld.updateTimer(30);
			break;
		case PLACE:
			//place and customise roboticons
			GameWorld.updateTimer(30);
			break;
		case END:
			// final state (updates gamestate Machine)
			GameWorld.nextGameState();
			break;
		}

	}

}
