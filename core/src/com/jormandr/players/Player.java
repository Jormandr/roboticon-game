package com.jormandr.players;

import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.Plot;

/**
 * Player class contains all methods used by Players to play the game
 *
 */
public abstract class Player {

	public enum PlayerState {
		PLOT, BUY, PLACE, END;
	}

	protected PlayerState playerState;
	private int score, ore, food, energy, money, roboticonsOwned;
	private final int mapSize = GameConfig.getMapHeight() * GameConfig.getMapWidth();
	private Plot[] plotsOwned = new Plot[mapSize];
	private int playerNumber;
	
	
	/**
	 * Constructor for player sets up resources and helpers
	 * 
	 * @param score
	 * @param ore
	 * @param food
	 * @param energy
	 * @param money
	 * @param roboticonsOwned
	 * @param playerNumber
	 */
	public Player(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber) {
		this.score = score;
		this.ore = ore;
		this.food = food;
		this.energy = energy;
		this.money = money;
		this.roboticonsOwned = roboticonsOwned;
		this.playerNumber = playerNumber;
		this.playerState = PlayerState.END;

	}
	
	/**
	 * Player state machine controls what player can do when it is their turn
	 */
	public void playerStateMachine() {
		switch (playerState) {
		case PLOT:
			// buy plots of land
			break;
		case BUY:
			// buying roboticons and gambling
			break;
		case PLACE:
			// customising and placing roboticons
			break;
		case END:
			// final state (updates gamestate Machine)
			break;

		}

	}

	/**
	 * returns the player's number
	 * 
	 * @return the player's number
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * sets the player's number
	 * 
	 * @param playerNumber
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}


	/**
	 * returns owned plots
	 * 
	 * @return owned plots
	 */
	public Plot[] getPlotsOwned() {
		return plotsOwned;
	}

	/**
	 * sets owned plots
	 * 
	 * @param plotsOwned
	 */
	public void setPlotsOwned(Plot[] plotsOwned) {
		this.plotsOwned = plotsOwned;
	}

	/**
	 * adds plot to owned plots
	 * 
	 * @param newPlot
	 */
	public void addPlot(Plot newPlot) {
		for (int i = 0; i < mapSize; i++) {
			if (plotsOwned[i] == null) {
				plotsOwned[i] = newPlot;
				break;
			}
		}
	}

	/**
	 * removes plot from owned plots
	 * 
	 * @param targetPlot
	 */
	public void removePlot(Plot targetPlot) {
		for (int i = 0; i < mapSize; i++) {
			if (plotsOwned[i] == targetPlot) {
				plotsOwned[i] = null;
				break;
			}
		}
	}

	/**
	 * returns player's score
	 * 
	 * @return player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * sets player's score
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * adds the difference to player's score
	 * 
	 * @param difference
	 */
	public void changeScore(int difference) {
		score += difference;
	}

	/**
	 * returns player's ore value
	 * 
	 * @return player's ore value
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * sets player's ore value
	 * 
	 * @param ore
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}

	/**
	 * adds the difference to player's ore value
	 * 
	 * @param difference
	 */
	public void changeOre(int difference) {
		ore += difference;
	}

	/**
	 * returns player's food value
	 * 
	 * @return player's food value
	 */
	public int getFood() {
		return food;
	}

	/**
	 * sets player's food value
	 * 
	 * @param food
	 */
	public void setFood(int food) {
		this.food = food;
	}

	/**
	 * adds difference to player's food value
	 * 
	 * @param difference
	 */
	public void changeFood(int difference) {
		food += difference;
	}

	/**
	 * returns player's energy value
	 * 
	 * @return
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * sets player's energy value
	 * 
	 * @param energy
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * adds difference to player's energy value
	 * 
	 * @param difference
	 */
	public void changeEnergy(int difference) {
		energy += difference;
	}

	/**
	 * returns player's money value
	 * 
	 * @return player's money value
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * sets player's money value
	 * 
	 * @param money
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * adds difference to player's money value
	 * 
	 * @param difference
	 */
	public void changeMoney(int difference) {
		money += difference;
	}

	/**
	 * adds difference to money and returns value 
	 * @param difference
	 * @return the value of money would be if you added change to it
	 */
	public int getChangeMoney(int difference) {
		return money + difference;
	}

	/**
	 * returns number of roboticons owned by player
	 * 
	 * @return number of roboticons owned by player
	 */
	public int getRoboticonsOwned() {
		return roboticonsOwned;
	}

	/**
	 * sets number of roboticons owned by player
	 * 
	 * @param roboticonsOwned
	 */
	public void setRoboticonsOwned(int roboticonsOwned) {
		this.roboticonsOwned = roboticonsOwned;
	}

	/**
	 * adds difference to number of roboticons owned by player
	 * 
	 * @param difference
	 */
	public void changeRoboticonsOwned(int difference) {
		roboticonsOwned += difference;
	}


	/**
	 * calculates score of player
	 * <p> logarithmically reduces value of plots to encourage buying of roboticons
	 */
	public void updateScore() {
		score = (int) ((ore + food + energy) * java.lang.Math.log10(plotsOwned.length));
	}

	/**
	 * sets the player state machine to "state"
	 * @param state
	 */
	public void setState(PlayerState state) {
		playerState = state;

	}

	/**
	 * returns the current player state (from player state machine)
	 * @return
	 */
	public PlayerState getState() {
		return playerState;
	}

	/**
	 * goes to next state in player state machine (this method loops to beginning)
	 */
	public void nextState() {
		playerState = PlayerState.values()[(playerState.ordinal() + 1) % PlayerState.values().length];
	}

	/**
	 * adds difference to food and returns value 
	 * @param difference
	 * @return the value of food would be if you added change to it
	 */
	public int getChangeFood(int difference) {
		return food + difference;
	}
	/**
	 * adds difference to energy and returns value 
	 * @param difference
	 * @return the value of energy would be if you added change to it
	 */
	public int getChangeEnergy(int difference) {
		return energy + difference;
	}
	/**
	 * adds difference to ore and returns value 
	 * @param difference
	 * @return the value of ore would be if you added change to it
	 */
	public int getChangeOre(int difference) {
		return ore + difference;
	}

}
