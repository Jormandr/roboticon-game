package com.jormandr.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.helpers.AssetLoader;

/**
 * Market used to trade resources and buy roboticons from
 *
 */
public class Market {
	
	public final static int INIT_FOOD = 100;
	public final static int INIT_ORE = 100;
	public final static int INIT_ENERGY = 100;
	public final static int INIT_ROBOS = 10;
	public final static float INIT_FOODBV = 10.0f;
	public final static float INIT_OREBV = 10.0f;
	public final static float INIT_ENERGYBV = 10.0f;
	public final static float INIT_ROBOSV = 10.0f;

	/**
	 * Enumerator for the different resource types
	 *
	 */
	public enum ResourceType {
		FOOD, ORE, ENERGY;
	}
	
	private static int[] value = new int[] {INIT_FOOD, INIT_ORE, INIT_ENERGY, INIT_ROBOS};
	private static float[] buyValue = new float[] {INIT_FOODBV, INIT_OREBV, INIT_ENERGYBV};
	private static float[] sellValue = new float[] {INIT_FOODBV * 1.5f, INIT_OREBV * 1.5f, INIT_ENERGYBV * 1.5f, INIT_ROBOSV};

	/**
	 *  Updates the market's stock for all resources and roboticons
	 */
	public static void update() {
		value[3] = (int) value[1] / 4; //converting ore to roboticons
		value[1] = 0;
		value[0] = 100;
		value[2] = 100;
	}

	/**
	 * sets value[0] aka amount of food in inventory
	 * @param value
	 */
	public static void setFood(int value) {
		Market.value[0] = value;
	}

	/**
	 * sets value[1] aka amount of ore in inventory
	 * @param value
	 */
	public static void setOre(int value) {
		Market.value[1] = value;
	}

	/**
	 * sets value[2] aka amount of energy in inventory
	 * @param value
	 */
	public static void setEnergy(int value) {
		Market.value[2] = value;
	}

	/**
	 * sets value[3] aka number of roboticons in inventory
	 * @param value
	 */
	public static void setRoboticons(int value) {
		Market.value[3] = value;
	}

	/**
	 * returns amount of food in inventory
	 * @return value[0]
	 */
	public static int getFood() {
		return value[0];
	}

	/**
	 * returns amount of ore in inventory
	 * @return value[1]
	 */
	public static int getOre() {
		return value[1];
	}

	/**
	 * returns amount of energy in inventory
	 * @return value[2]
	 */
	public static int getEnergy() {
		return value[2];
	}

	/**
	 * returns number of roboticons in inventory
	 * @return value[3]
	 */
	public static int getRoboticons() {
		return value[3];
	}

	/**
	 * returns price market buys food at
	 * @return buyValue[0]
	 */
	public static int getFoodBuyValue() {
		return (int) buyValue[0];
	}

	/**
	 * returns price market buys ore at
	 * @return buyValue[1]
	 */
	public static int getOreBuyValue() {
		return (int) buyValue[1];
	}

	/**
	 * returns price market buys energy at
	 * @return buyValue[2]
	 */
	public static int getEnergyBuyValue() {
		return (int) buyValue[2];
	}

	/**
	 * returns price market sells food at
	 * @return sellValue[0]
	 */
	public static int getFoodSellValue() {
		return (int) sellValue[0];
	}

	/**
	 * returns price market sells ore at
	 * @return sellValue[1]
	 */
	public static int getOreSellValue() {
		return (int) sellValue[1];
	}

	/**returns price market sells energy at
	 * @return sellValue[2]
	 */
	public static int getEnergySellValue() {
		return (int) sellValue[2];
	}

	/**
	 * returns price market sells roboticons at
	 * @return sellValue[3]
	 */
	public static int getRoboticonSellValue() {
		return (int) sellValue[3];
	}
	
	/**
	 * adds the difference to the food inventory
	 * @param difference
	 */
	public static void changeFood(int difference) {
		value[0] += difference;
	}

	/**
	 * adds the difference to the ore inventory
	 * @param difference
	 */
	public static void changeOre(int difference) {
		value[1] += difference;
	}
	
	/**
	 * adds the difference to the energy inventory
	 * @param difference
	 */
	public static void changeEnergy(int difference) {
		value[2] += difference;
	}
	
	/**
	 * adds the difference to the number of roboticons in inventory
	 * @param difference
	 */
	public static void changeRoboticons(int difference) {
		value[3] += difference;

	}

	/**
	 * draw method to be called in GameRenderer
	 * @param batcher
	 */
	public static void draw(SpriteBatch batcher) {

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
