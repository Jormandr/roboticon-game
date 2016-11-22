package com.jormandr.players;

public abstract class Player {
	
	private int score, ore, food, energy, money, roboticonsOwned;
	// Todo: Implement plotsOwned
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}
	
	public void changeScore(int difference) {
		score += difference;
	}
	
	
	
	public int getOre() {
		return ore;
	}
	
	public void setOre(int newOre) {
		ore = newOre;
	}
	
	public void changeOre(int difference) {
		ore += difference;
	}
	
	
	
	public int getFood() {
		return food;
	}
	
	public void setFood(int newFood) {
		food = newFood;
	}
	
	public void changeFood(int difference) {
		food += difference;
	}
	
	
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int newEnergy) {
		energy = newEnergy;
	}
	
	public void changeEnergy(int difference) {
		energy += difference;
	}
	
	
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int newMoney) {
		money = newMoney;
	}
	
	public void changeMoney(int difference) {
		money += difference;
	}
	
	
	
	public int getRoboticons() {
		return roboticonsOwned;
	}
	
	public void setRoboticons(int newRoboticons) {
		roboticonsOwned = newRoboticons;
	}
	
	public void changeRoboticons(int difference) {
		roboticonsOwned += difference;
	}

}
