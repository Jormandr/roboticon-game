package com.jormandr.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.TileType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.players.Player;
import static com.jormandr.misctypes.UtilityFunctions.floatEq;

/**
 * In this plot class, characteristics specific to plot tiles are defined such
 * as: ownership, resource and production values.
 */
public class Plot extends MapTile {

	/**
	 * Potential idea: Group these into vectors or something - would look nicer
	 * but this may add overhead
	 */
	
	private ResourceTriple ore, food, energy;
	private int cost = 10;
	private Player owned = null;
	
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
	private boolean hasRoboticonCache = false;

	public Plot(float i, float j, int oreValue, int foodValue, int energyValue, TileType type) {
		/**
		 * in here, figure out what type tile I am, what resource values I have,
		 * where I am located
		 */
		super(i, j, type);
		ore = new ResourceTriple(oreValue);
		food = new ResourceTriple(foodValue);
		energy = new ResourceTriple(energyValue);
	}

	/**
	 * returns ore value for this round
	 * 
	 * @return ore value for this round
	 */
	public int getOreValue() {
		return ore.getValue();
	}

	/**
	 * returns food value for this round
	 * 
	 * @return food value for this round
	 */
	public int getFoodValue() {
		return food.getValue();
	}

	/**
	 * returns energy value for this round
	 * 
	 * @return energy value for this round
	 */
	public int getEnergyValue() {
		return energy.getValue();
	}

	/**
	 * returns ore resource buff multiplier
	 * 
	 * @return ore resource buff multiplier
	 */
	public float getOreBuff() {
		return ore.getBuff();
	}

	/**
	 * sets the ore resource buff multiplier
	 * 
	 * @param buff
	 */
	public void setOreBuff(float buff) {
		ore.setBuff(buff);
		updateHasRoboticonCache();
	}
	
	/**
	 * add to the ore resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeOreBuff(float buff) {
		ore.changeBuff(buff);
		updateHasRoboticonCache();
	}

	/**
	 * returns ore resource debuff multiplier
	 * 
	 * @return ore resource debuff multiplier
	 */
	public float getOreDebuff() {
		return ore.getDebuff();
	}

	/**
	 * sets the ore resource debuff multiplier
	 * 
	 * @param debuff
	 */
	public void setOreDebuff(float debuff) {
		ore.setDebuff(debuff);
	}
	
	/**
	 * add to the ore resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeOreDebuff(float debuff) {
		ore.changeDebuff(debuff);
	}

	/**
	 * returns the food resource buff multiplier
	 * 
	 * @return the food resource buff multiplier
	 */
	public float getFoodBuff() {
		return food.getBuff();
	}

	/**
	 * sets the food resource buff multiplier
	 * 
	 * @param buff
	 */
	public void setFoodBuff(float buff) {
		food.setBuff(buff);
		updateHasRoboticonCache();
	}
	
	/**
	 * add to the food resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeFoodBuff(float buff) {
		food.changeBuff(buff);
		updateHasRoboticonCache();
	}

	/**
	 * returns the food resource debuff multiplier
	 * 
	 * @return the food resource debuff multiplier
	 */
	public float getFoodDebuff() {
		return food.getDebuff();
	}

	/**
	 * sets the food resource debuff multiplier
	 * 
	 * @param debuff
	 */
	public void setFoodDebuff(float debuff) {
		food.setDebuff(debuff);
	}
	
	/**
	 * add to the food resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeFoodDebuff(float debuff) {
		food.changeDebuff(debuff);
	}

	/**
	 * returns the energy resource buff multiplier
	 * 
	 * @return the energy resource buff multiplier
	 */
	public float getEnergyBuff() {
		return energy.getBuff();
	}

	/**
	 * sets the energy resource buff multiplier
	 * 
	 * @param buff
	 */
	public void setEnergyBuff(float buff) {
		energy.setBuff(buff);
		updateHasRoboticonCache();
	}
	
	/**
	 * add to the energy resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeEnergyBuff(float buff) {
		energy.changeBuff(buff);
		updateHasRoboticonCache();
	}

	/**
	 * returns the energy resource debuff multiplier
	 * 
	 * @return the energy resource debuff multiplier
	 */
	public float getEnergyDebuff() {
		return energy.getDebuff();
	}

	/**
	 * sets the energy resource debuff multiplier
	 * 
	 * @param debuff
	 */
	public void setEnergyDebuff(float debuff) {
		energy.setDebuff(debuff);
	}
	
	/**
	 * add to the energy resource buff multiplier
	 * 
	 * @param buff
	 */
	public void changeEnergyDebuff(float debuff) {
		energy.changeDebuff(debuff);
	}

	/**
	 * Remove the Roboticon by setting all buffs to 1.0f. This is not only less
	 * typing but is also quicker than setting the buffs with the public
	 * interfaces
	 */
	public void removeRoboticon() {
		energy.setBuff(1.0f);
		ore.setBuff(1.0f);
		food.setBuff(1.0f);
		hasRoboticonCache = false;
	}

	private void updateHasRoboticonCache() {
		hasRoboticonCache = !(floatEq(ore.getBuff(), 1.0f) && floatEq(food.getBuff(), 1.0f) && floatEq(energy.getBuff(), 1.0f));
	}

	/**
	 * returns 1 if a roboticon is placed on this tile. Determination is done by
	 * checking if each buff is 0.9f < x < 0.1f. To avoid expensive floating
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
	public void draw(SpriteBatch batcher, int yOffset){

		if (owned == GameWorld.getPlayer(GameWorld.GameState.HANDLINGP1)) {
			batcher.setColor(1.0f, 0.5f, 0.5f, 1.0f);

		} else if (owned == GameWorld.getPlayer(GameWorld.GameState.HANDLINGP2)) {
			batcher.setColor(0.5f, 0.5f, 1.0f, 1.0f);
		}

		batcher.draw(AssetLoader.textureMap[getType().ordinal()], convertToX(), convertToY()+yOffset,ww,0, 62, 34,2,2,0);

		batcher.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		if (hasRoboticon()) {
			batcher.draw(AssetLoader.roboticon, convertToX(), convertToY()+yOffset,ww/2,0,191-164,24,2,2,0);
		}
	}

	public void setOwned(Player player) {
		owned = player;
	}

	public int getCost() {
		return cost;
	}

	public Player getOwned() {
		return owned;
	}

}
