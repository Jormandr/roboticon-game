package com.jormandr.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.helpers.AssetLoader;

/**
 * Market used to trade resources and buy roboticons from
 *
 */
public class Market {

	/**
	 * Enumerator for the different resource types
	 *
	 */
	public enum ResourceType {
		FOOD, ORE, ENERGY;
	}

	private int[] value = new int[4];
	private float[] buyValue = new float[3];
	private float[] sellValue = new float[3];

	/**
	 * Initialises the market buy and sell values
	 * how much of each resource and roboticons it has.
	 * 
	 * @param food
	 * @param ore
	 * @param energy
	 * @param roboticons
	 * @param foodBuyValue
	 * @param oreBuyValue
	 * @param energyBuyValue
	 * @param roboticonsSellValue
	 */
	public Market(int food, int ore, int energy, int roboticons, float foodBuyValue, float oreBuyValue,
			float energyBuyValue, float roboticonsSellValue) {

		value = new int[] { food, ore, energy, roboticons };
		buyValue = new float[] { foodBuyValue, oreBuyValue, energyBuyValue };
		sellValue = new float[] { foodBuyValue * 1.5f, oreBuyValue * 1.5f, energyBuyValue * 1.5f, roboticonsSellValue };

	}

	/**
	 *  Updates the market's stock for all resources and roboticons
	 */
	public void update() {
		value[3] = (int) value[1] / 4; //converting ore to roboticons
		value[1] = 0;
		value[0] = 100;
		value[2] = 100;
	}

	/**
	 * sets value[0] aka amount of food in inventory
	 * @param value
	 */
	public void setFood(int value) {
		this.value[0] = value;
	}

	/**
	 * sets value[1] aka amount of ore in inventory
	 * @param value
	 */
	public void setOre(int value) {
		this.value[1] = value;
	}

	/**
	 * sets value[2] aka amount of energy in inventory
	 * @param value
	 */
	public void setEnergy(int value) {
		this.value[2] = value;
	}

	/**
	 * sets value[3] aka number of roboticons in inventory
	 * @param value
	 */
	public void setRoboticons(int value) {
		this.value[3] = value;
	}

	/**
	 * returns amount of food in inventory
	 * @return value[0]
	 */
	public int getFood() {
		return value[0];
	}

	/**
	 * returns amount of ore in inventory
	 * @return value[1]
	 */
	public int getOre() {
		return value[1];
	}

	/**
	 * returns amount of energy in inventory
	 * @return value[2]
	 */
	public int getEnergy() {
		return value[2];
	}

	/**
	 * returns number of roboticons in inventory
	 * @return value[3]
	 */
	public int getRoboticons() {
		return value[3];
	}

	/**
	 * returns price market buys food at
	 * @return buyValue[0]
	 */
	public int getFoodBuyValue() {
		return (int) buyValue[0];
	}

	/**
	 * returns price market buys ore at
	 * @return buyValue[1]
	 */
	public int getOreBuyValue() {
		return (int) buyValue[1];
	}

	/**
	 * returns price market buys energy at
	 * @return buyValue[2]
	 */
	public int getEnergyBuyValue() {
		return (int) buyValue[2];
	}

	/**
	 * returns price market sells food at
	 * @return sellValue[0]
	 */
	public int getFoodSellValue() {
		return (int) sellValue[0];
	}

	/**
	 * returns price market sells ore at
	 * @return sellValue[1]
	 */
	public int getOreSellValue() {
		return (int) sellValue[1];
	}

	/**returns price market sells energy at
	 * @return sellValue[2]
	 */
	public int getEnergySellValue() {
		return (int) sellValue[2];
	}

	/**
	 * returns price market sells roboticons at
	 * @return sellValue[3]
	 */
	public int getRoboticonSellValue() {
		return (int) sellValue[3];
	}
	
	/**
	 * adds the difference to the food inventory
	 * @param difference
	 */
	public void changeFood(int difference) {
		value[0] += difference;
	}

	/**
	 * adds the difference to the ore inventory
	 * @param difference
	 */
	public void changeOre(int difference) {
		value[1] += difference;
	}
	
	/**
	 * adds the difference to the energy inventory
	 * @param difference
	 */
	public void changeEnergy(int difference) {
		value[1] += difference;
	}
	
	/**
	 * adds the difference to the number of roboticons in inventory
	 * @param difference
	 */
	public void changeRoboticons(int difference) {
		value[3] += difference;

	}

	/**
	 * draw method to be called in GameRenderer
	 * @param batcher
	 */
	public void draw(SpriteBatch batcher) {

		AssetLoader.fontX.draw(batcher, "Market", 600, 232);
		AssetLoader.fontX.draw(batcher, "BUY: RESOURCE: SELL: STOCK", 380, 265);
		AssetLoader.fontX.draw(batcher, getFoodBuyValue() + "    :Food:     " + getFoodSellValue() + "   " + getFood(),
				380, 280);
		AssetLoader.fontX.draw(batcher, getOreBuyValue() + "    :Ore:      " + getOreSellValue() + "   " + getOre(),
				380, 295);
		AssetLoader.fontX.draw(batcher,
				getEnergyBuyValue() + "    :Energy:   " + getEnergySellValue() + "   " + getEnergy(), 380, 310);
		AssetLoader.fontX.draw(batcher, "Roboticons in stock: " + getRoboticons(), 380, 340);
		AssetLoader.fontX.draw(batcher, "Cost: " + getRoboticonSellValue(), 380, 350);
	}



}
