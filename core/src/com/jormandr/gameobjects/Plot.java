package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.jormandr.gameobjects.TileType;

/**
 * In this plot class, characteristics specific to plot tiles are defined such
 * as: ownership, resource and production values.
 */
public class Plot extends MapTile {

	/**
	 * Potential idea: Group these into vectors or something - would look nicer
	 * but this may add overhead
	 */

	private final int oreValue, foodValue, energyValue;
	private float oreBuff = 1.0f;
	private float oreDebuff = 1.0f;
	private float foodBuff = 1.0f;
	private float foodDebuff = 1.0f;
	private float energyBuff = 1.0f;
	private float energyDebuff = 1.0f;

	public Plot(float i, float j, int oreValue, int foodValue, int energyValue, TileType type, float[] verts) {
		/**
		 * in here, figure out what type tile I am, what resource values I have,
		 * where I am located
		 */
		super(i, j, type, verts);
		this.oreValue = oreValue;
		this.foodValue = foodValue;
		this.energyValue = energyValue;
		Gdx.app.log("Plot", "New tile created at " + i + ", " + j + " of type " + type);
	}

	/**
	 * calculates new resource value dependent on random events
	 * 
	 * @param value
	 * @param buff
	 * @param debuff
	 * @return new resource value for this round
	 */
	private int calculateValue(int value, float buff, float debuff) {
		// The corresponding test class has a copy of this function as it reads
		// now
		// If you optimise this function, leave it be
		// If you change the function, change the test too
		return (int) (buff * debuff * value + 0.5f);
	}

	/**
	 * returns ore value for this round
	 * 
	 * @return ore value for this round
	 */
	public int getOreValue() {
		return calculateValue(oreValue, oreBuff, oreDebuff);
	}

	/**
	 * returns food value for this round
	 * 
	 * @return food value for this round
	 */
	public int getFoodValue() {
		return calculateValue(foodValue, foodBuff, foodDebuff);
	}

	/**
	 * returns energy value for this round
	 * 
	 * @return energy value for this round
	 */
	public int getEnergyValue() {
		return calculateValue(energyValue, energyBuff, energyDebuff);
	}

	/**
	 * returns ore resource buff multiplier
	 * 
	 * @return ore resource buff multiplier
	 */
	public float getOreBuff() {
		return oreBuff;
	}

	/**
	 * sets the ore resource buff multiplier
	 * 
	 * @param oreBuff
	 */
	public void setOreBuff(float oreBuff) {
		this.oreBuff = oreBuff;
	}

	/**
	 * returns ore resource debuff multiplier
	 * 
	 * @return ore resource debuff multiplier
	 */
	public float getOreDebuff() {
		return oreDebuff;
	}

	/**
	 * sets the ore resource debuff multiplier
	 * 
	 * @param oreDebuff
	 */
	public void setOreDebuff(float oreDebuff) {
		this.oreDebuff = oreDebuff;
	}

	/**
	 * returns the food resource buff multiplier
	 * 
	 * @return the food resource buff multiplier
	 */
	public float getFoodBuff() {
		return foodBuff;
	}

	/**
	 * sets the food resource buff multiplier
	 * 
	 * @param foodBuff
	 */
	public void setFoodBuff(float foodBuff) {
		this.foodBuff = foodBuff;
	}

	/**
	 * returns the food resource debuff multiplier
	 * 
	 * @return the food resource debuff multiplier
	 */
	public float getFoodDebuff() {
		return foodDebuff;
	}

	/**
	 * sets the food resource debuff multiplier
	 * 
	 * @param foodDebuff
	 */
	public void setFoodDebuff(float foodDebuff) {
		this.foodDebuff = foodDebuff;
	}

	/**
	 * returns the energy resource buff multiplier
	 * 
	 * @return the energy resource buff multiplier
	 */
	public float getEnergyBuff() {
		return energyBuff;
	}

	/**
	 * sets the energy resource buff multiplier
	 * 
	 * @param energyBuff
	 */
	public void setEnergyBuff(float energyBuff) {
		this.energyBuff = energyBuff;
	}

	/**
	 * returns the energy resource debuff multiplier
	 * 
	 * @return the energy resource debuff multiplier
	 */
	public float getEnergyDebuff() {
		return energyDebuff;
	}

	/**
	 * sets the energy resource debuff multiplier
	 * 
	 * @param energyDebuff
	 */
	public void setEnergyDebuff(float energyDebuff) {
		this.energyDebuff = energyDebuff;
	}

	/**
	 * returns 1 if a roboticon is placed on this tile
	 * 
	 * @return 1 if a roboticon is placed on this tile
	 */
	public boolean hasRoboticon() {
		// Test this rigorously
		return !(oreBuff == 1.0f && foodBuff == 1.0f && energyBuff == 1.0f);
	}

}
