package com.jormandr.testing.gameobjects;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.gameobjects.Market;

import java.util.Random;

public class MarketTest {

	private final int ITERATIONS = 50;
	private final int RANDOM_INT_LIMIT = 255;

	private Random rand = new Random();

	private Market market;
	private int food, ore, energy, roboticons, roboticonsDelta;
	private float foodBuyValue, oreBuyValue, energyBuyValue, roboticonsSellValue;

	@Test
	public void mainMarketTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			food = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			roboticons = rand.nextInt(RANDOM_INT_LIMIT);
			foodBuyValue = rand.nextFloat();
			oreBuyValue = rand.nextFloat();
			energyBuyValue = rand.nextFloat();
			roboticonsSellValue = rand.nextFloat();

			market = new Market(food, ore, energy, roboticons, foodBuyValue, oreBuyValue, energyBuyValue,
					roboticonsSellValue);

			assertTrue(market.getFood() == food);
			assertTrue(market.getOre() == ore);
			assertTrue(market.getEnergy() == energy);
			assertTrue(market.getRoboticons() == roboticons);
			assertTrue(market.getFoodBuyValue() == (int) foodBuyValue);
			assertTrue(market.getFoodSellValue() == (int) (foodBuyValue * 1.5f));
			assertTrue(market.getEnergyBuyValue() == (int) energyBuyValue);
			assertTrue(market.getEnergySellValue() == (int) (energyBuyValue * 1.5f));
			assertTrue(market.getOreBuyValue() == (int) oreBuyValue);
			assertTrue(market.getOreSellValue() == (int) (oreBuyValue * 1.5f));

			food = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			roboticons = rand.nextInt(RANDOM_INT_LIMIT);

			market.setFood(food);
			market.setOre(ore);
			market.setEnergy(energy);
			market.setRoboticons(roboticons);

			assertTrue(market.getFood() == food);
			assertTrue(market.getOre() == ore);
			assertTrue(market.getEnergy() == energy);
			assertTrue(market.getRoboticons() == roboticons);

			roboticonsDelta = rand.nextInt(RANDOM_INT_LIMIT);
			market.changeRoboticons(roboticonsDelta);
			assertTrue(market.getRoboticons() == roboticons + roboticonsDelta);
		}

	}
}
