package com.jormandr.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.GameState;
import com.jormandr.helpers.GameStateHandler;
import com.jormandr.helpers.InputHandler;
import com.jormandr.playerstates.state0;

public abstract class Player {
	private int score, ore, food, energy, money, roboticonsOwned;
	private final int mapSize = GameConfig.getMapHeight() * GameConfig.getMapWidth();
	private Plot[] plotsOwned = new Plot[mapSize];
	private int playerNumber;
	private GameStateHandler gsh;
	private int playerState = 0;
	InputHandler test = new InputHandler();
	private int one, two, three, four;

	public void playerStateMachine() {
		switch (playerState) {
		case 0:
			// buy plots of land
			Gdx.input.setInputProcessor(test);

			state0.test();
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

	private void buyPlots() {

	}

	private void buyRoboticons() {

	}

	private void customiseRoboticons() {

	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public Player(int score, int ore, int food, int energy, int money, int roboticonsOwned, int playerNumber,
			GameStateHandler gsh) {
		this.score = score;
		this.ore = ore;
		this.food = food;
		this.energy = energy;
		this.money = money;
		this.roboticonsOwned = roboticonsOwned;
		this.playerNumber = playerNumber;
		this.gsh = gsh;

	}

	public Plot[] getPlotsOwned() {
		return plotsOwned;
	}

	public void setPlotsOwned(Plot[] plotsOwned) {
		this.plotsOwned = plotsOwned;
	}

	public void addPlot(Plot newPlot) {
		// TODO add exception if there is no space
		for (int i = 0; i < mapSize; i++) {
			if (plotsOwned[i] == null) {
				plotsOwned[i] = newPlot;
				break;
			}
		}
	}

	public void removePlot(Plot targetPlot) {
		// TODO add exception
		for (int i = 0; i < mapSize; i++) {
			if (plotsOwned[i] == targetPlot) {
				plotsOwned[i] = null;
				break;
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void changeScore(int difference) {
		score += difference;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public void changeOre(int difference) {
		ore += difference;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public void changeFood(int difference) {
		food += difference;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void changeEnergy(int difference) {
		energy += difference;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void changeMoney(int difference) {
		money += difference;
	}

	public int getRoboticonsOwned() {
		return roboticonsOwned;
	}

	public void setRoboticonsOwned(int roboticonsOwned) {
		this.roboticonsOwned = roboticonsOwned;
	}

	public void changeRoboticonsOwned(int difference) {
		roboticonsOwned += difference;
	}

	public void update() {
		if ((playerNumber == 1 && gsh.getGameState() == GameState.WAITINGFORP1.ordinal())
				|| (playerNumber == 2 && gsh.getGameState() == GameState.WAITINGFORP2.ordinal())) {
			// Deal with turn
			gsh.incrementGameState();
		}
	}

}
