package com.jormandr.players;

import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.Plot;

public abstract class Player {

	private int score, ore, food, energy, money, roboticonsOwned;
	private final int mapSize = GameConfig.getMapHeight()*GameConfig.getMapWidth();
	Plot[] plotsOwned = new Plot[mapSize];

	public Player(int score, int ore, int food, int energy, int money, int roboticonsOwned) {
		this.score = score;
		this.ore = ore;
		this.food = food;
		this.energy = energy;
		this.money = money;
		this.roboticonsOwned = roboticonsOwned;
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

	public void makeTurn() {
		// TODO Auto-generated method stub
		
	}

}
