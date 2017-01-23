package com.jormandr.testing.gameobjects;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.gameobjects.Market;

import java.util.Random;

public class MarketTest {

	private final int ITERATIONS = 50;
	private final int RANDOM_INT_LIMIT = 255;

	private Random rand = new Random();

	private int food, ore, energy, roboticons, roboticonsDelta;
	private float foodBuyValue, oreBuyValue, energyBuyValue, roboticonsSellValue;

	@Test
	public void mainMarketTest() {
		// Test initial settings
		
		food = Market.INIT_FOOD;
		ore = Market.INIT_ORE;
		energy = Market.INIT_ENERGY;
		roboticons = Market.INIT_ROBOS;
		foodBuyValue = Market.INIT_FOODBV;
		oreBuyValue = Market.INIT_OREBV;
		energyBuyValue = Market.INIT_ENERGYBV;
		roboticonsSellValue = Market.INIT_ROBOSV;
		
		assertTrue(Market.getFood() == food);
		assertTrue(Market.getOre() == ore);
		assertTrue(Market.getEnergy() == energy);
		assertTrue(Market.getRoboticons() == roboticons);
		assertTrue(Market.getFoodBuyValue() == (int) foodBuyValue);
		assertTrue(Market.getFoodSellValue() == (int) (foodBuyValue * 1.5f));
		assertTrue(Market.getEnergyBuyValue() == (int) energyBuyValue);
		assertTrue(Market.getEnergySellValue() == (int) (energyBuyValue * 1.5f));
		assertTrue(Market.getOreBuyValue() == (int) oreBuyValue);
		assertTrue(Market.getOreSellValue() == (int) (oreBuyValue * 1.5f));
		assertTrue(Market.getRoboticonSellValue() == (int) (roboticonsSellValue));
		
		for (int counter = 0; counter < ITERATIONS; counter++) {
			food = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			roboticons = rand.nextInt(RANDOM_INT_LIMIT);
			
			Market.setFood(food);
			Market.setOre(ore);
			Market.setEnergy(energy);
			Market.setRoboticons(roboticons);

			assertTrue(Market.getFood() == food);
			assertTrue(Market.getOre() == ore);
			assertTrue(Market.getEnergy() == energy);
			assertTrue(Market.getRoboticons() == roboticons);
			assertTrue(Market.getFoodBuyValue() == (int) foodBuyValue);
			assertTrue(Market.getFoodSellValue() == (int) (foodBuyValue * 1.5f));
			assertTrue(Market.getEnergyBuyValue() == (int) energyBuyValue);
			assertTrue(Market.getEnergySellValue() == (int) (energyBuyValue * 1.5f));
			assertTrue(Market.getOreBuyValue() == (int) oreBuyValue);
			assertTrue(Market.getOreSellValue() == (int) (oreBuyValue * 1.5f));
			assertTrue(Market.getRoboticonSellValue() == (int) (roboticonsSellValue));

			food = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			roboticons = rand.nextInt(RANDOM_INT_LIMIT);

			Market.setFood(food);
			Market.setOre(ore);
			Market.setEnergy(energy);
			Market.setRoboticons(roboticons);

			assertTrue(Market.getFood() == food);
			assertTrue(Market.getOre() == ore);
			assertTrue(Market.getEnergy() == energy);
			assertTrue(Market.getRoboticons() == roboticons);

			roboticonsDelta = rand.nextInt(RANDOM_INT_LIMIT);
			Market.changeRoboticons(roboticonsDelta);
			assertTrue(Market.getRoboticons() == roboticons + roboticonsDelta);
		}

	}
}
