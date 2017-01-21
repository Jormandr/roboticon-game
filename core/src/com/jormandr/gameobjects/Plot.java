package com.jormandr.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.TileType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;

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
	// OK, so a Roboticon isn't actually a "thing", it's basically a triple of
	// buffs
	// To add one you assign its triple to the plot's buffs
	// To remove it you reset to (1, 1, 1)
	// Therefore checking if a plot has one is done by comparing the buff values
	// with (1, 1, 1), which is done with a margin of error because floating
	// point
	// Since this is expensive, this boolean result is cached into a private
	// bool, updated upon buff mutation, which can then be returned quickly by
	// hasRoboticon()
	private float oreBuff = 1.0f;
	private float oreDebuff = 1.0f;
	private float foodBuff = 1.0f;
	private float foodDebuff = 1.0f;
	private float energyBuff = 1.0f;
	private float energyDebuff = 1.0f;
	private int cost = 10;
	private boolean hasRoboticonCache = false;
	// A similar space-time tradeoff is used to cache resource value
	private int oreValueCache, foodValueCache, energyValueCache;
	private Player owned = null;

	public Plot(float i, float j, int oreValue, int foodValue, int energyValue, TileType type) {
		/**
		 * in here, figure out what type tile I am, what resource values I have,
		 * where I am located
		 */
		super(i, j, type);
		this.oreValue = oreValue;
		updateOreValueCache();
		this.foodValue = foodValue;
		updateFoodValueCache();
		this.energyValue = energyValue;
		updateEnergyValueCache();
		// Gdx.app.log("Plot", "New tile created at " + i + ", " + j + " of type
		// " + type);
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
	
	private void updateOreValueCache() {
		oreValueCache = calculateValue(oreValue, oreBuff, oreDebuff);
	}

	private void updateFoodValueCache() {
		foodValueCache = calculateValue(foodValue, foodBuff, foodDebuff);
	}

	private void updateEnergyValueCache() {
		energyValueCache = calculateValue(energyValue, energyBuff, energyDebuff);
	}

	/**
	 * returns ore value for this round
	 * 
	 * @return ore value for this round
	 */
	public int getOreValue() {
		return oreValueCache;
	}

	/**
	 * returns food value for this round
	 * 
	 * @return food value for this round
	 */
	public int getFoodValue() {
		return foodValueCache;
	}

	/**
	 * returns energy value for this round
	 * 
	 * @return energy value for this round
	 */
	public int getEnergyValue() {
		return energyValueCache;
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
		updateHasRoboticonCache();
		updateOreValueCache();
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
		updateOreValueCache();
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
		updateHasRoboticonCache();
		updateFoodValueCache();
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
		updateFoodValueCache();
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
		updateHasRoboticonCache();
		updateEnergyValueCache();
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
		updateEnergyValueCache();
	}
	
	/**
	 * Remove the Roboticon by setting all buffs to 1.0f. This is not only less
	 * typing but is also quicker than setting the buffs with the public
	 * interfaces
	 */
	public void removeRoboticon() {
		energyBuff = 1.0f;
		oreBuff = 1.0f;
		foodBuff = 1.0f;
		hasRoboticonCache = false;
	}

	/**
	 * Compares floats for equality using a (hardcoded) margin of error
	 * 
	 * @param x
	 * @param y
	 * @return whether they are withing the margin
	 */
	private boolean floatEq(float x, float y) {
		return Math.abs(x - y) < 1.0f;
	}
	
	private void updateHasRoboticonCache() {
		hasRoboticonCache = !(floatEq(oreBuff, 1.0f) && floatEq(foodBuff, 1.0f) && floatEq(energyBuff, 1.0f));
	}

	/**
	 * returns 1 if a roboticon is placed on this tile. Determination is done by
	 * checking if each buff is 0.0f < x < 2.0f. To avoid expensive floating
	 * point calculations, the value of this function is calculated upon buff
	 * mutation and cached
	 * 
	 * @return 1 if a roboticon is believed to be placed on this tile
	 */
	public boolean hasRoboticon() {
		// Test this rigorously
		return hasRoboticonCache;
	}
	
	@Override
	public  void draw(SpriteBatch batcher, float xx, float yy, int yOffset) {
		
		if (owned == GameWorld.getPlayer(GameWorld.GameState.HANDLINGP1)){
			batcher.setColor(1.0f, 0.5f, 0.5f, 1.0f);
			
		} else if (owned== GameWorld.getPlayer(GameWorld.GameState.HANDLINGP2)){
			batcher.setColor(0.5f, 0.5f, 1.0f, 1.0f);
		}
		
		
		batcher.draw(AssetLoader.textureMap[getType().ordinal()], xx, yy + yOffset, 124, -68);
		batcher.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	public void setOwned(Player player){
		owned = player;
	}
	
	public int getCost(){
		return cost;
	}

	public Player getOwned() {
		return owned;
	}

}
