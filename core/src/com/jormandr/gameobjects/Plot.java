package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.jormandr.gameobjects.TileType;

public class Plot extends MapTile {

	// Potential idea: Group these into vectors or something - would look nicer
	// but this may add overhead
	private final int oreValue, foodValue, energyValue;
	private float oreBuff, oreDebuff, foodBuff, foodDebuff, energyBuff, energyDebuff = 1.0f;

	public Plot(float i, float j, int oreValue, int foodValue, int energyValue, TileType type, float[] verts) {
		// in here, figure out what type tile I am, what resource values I have,
		// where I am located
		super(i, j, type, verts);
		this.oreValue = oreValue;
		this.foodValue = foodValue;
		this.energyValue = energyValue;
		Gdx.app.log("Plot", "New tile created at " + i + ", " + j + " of type " + type);
	}

	private int calculateValue(int value, float buff, float debuff) {
		return (int) (buff * debuff * value + 0.5f);
	}

	public int getOreValue() {
		return calculateValue(oreValue, oreBuff, oreDebuff);
	}

	public int getFoodValue() {
		return calculateValue(foodValue, foodBuff, foodDebuff);
	}

	public int getEnergyValue() {
		return calculateValue(energyValue, energyBuff, energyDebuff);
	}

	public float getOreBuff() {
		return oreBuff;
	}

	public void setOreBuff(float oreBuff) {
		this.oreBuff = oreBuff;
	}

	public float getOreDebuff() {
		return oreDebuff;
	}

	public void setOreDebuff(float oreDebuff) {
		this.oreDebuff = oreDebuff;
	}

	public float getFoodBuff() {
		return foodBuff;
	}

	public void setFoodBuff(float foodBuff) {
		this.foodBuff = foodBuff;
	}

	public float getFoodDebuff() {
		return foodDebuff;
	}

	public void setFoodDebuff(float foodDebuff) {
		this.foodDebuff = foodDebuff;
	}

	public float getEnergyBuff() {
		return energyBuff;
	}

	public void setEnergyBuff(float energyBuff) {
		this.energyBuff = energyBuff;
	}

	public float getEnergyDebuff() {
		return energyDebuff;
	}

	public void setEnergyDebuff(float energyDebuff) {
		this.energyDebuff = energyDebuff;
	}

	public boolean hasRoboticon() {
		// Test this rigorously
		return !(oreBuff == 1.0f && foodBuff == 1.0f && energyBuff == 1.0f);
	}

}
