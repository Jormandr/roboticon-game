package com.jormandr.players;

public abstract class Player {

	private int score, ore, food, energy, money, roboticonsOwned;
	// TODO Implement plotsOwned

	public Player(int score, int ore, int food, int energy, int money, int roboticonsOwned) {
		this.score = score;
		this.ore = ore;
		this.food = food;
		this.energy = energy;
		this.money = money;
		this.roboticonsOwned = roboticonsOwned;
		// plotsOwned = newPlotsOwned
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

	public int getRoboticons() {
		return roboticonsOwned;
	}

	public void setRoboticons(int roboticonsOwned) {
		this.roboticonsOwned = roboticonsOwned;
	}

	public void changeRoboticons(int difference) {
		roboticonsOwned += difference;
	}

}
