package com.jormandr.testing.players;

import org.junit.Test;
import org.junit.runner.RunWith;
import de.tomgrill.gdxtesting.GdxTestRunner;
import static org.junit.Assert.assertEquals;

import com.jormandr.helpers.GameStateHandler;
import com.jormandr.players.HumanPlayer;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class HumanPlayerTest {
	private final int ITERATIONS = 5;
	private final int RANDOM_INT_LIMIT = 255;

	private Random rand = new Random();

	private GameStateHandler gsh = new GameStateHandler();
	private int score, ore, food, energy, money, roboticonsOwned, playerNumber, playerState;
	private int scoreD, oreD, foodD, energyD, moneyD, roboticonsOwnedD;

	@Test
	public void mainHumanPlayerTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			// Create random test cases
			score = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			food = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			money = rand.nextInt(RANDOM_INT_LIMIT);
			roboticonsOwned = rand.nextInt(RANDOM_INT_LIMIT);
			playerNumber = 1 + rand.nextInt(1);
			playerState = 1; // TODO ascertain the paramaters of this and how to test it

			// Set them
			HumanPlayer player = new HumanPlayer(score, ore, food, energy, money, roboticonsOwned, playerNumber);

			// Test them
			assertEquals(player.getEnergy(), energy);
			assertEquals(player.getFood(), food);
			assertEquals(player.getMoney(), money);
			assertEquals(player.getOre(), ore);
			assertEquals(player.getPlayerNumber(), playerNumber);
			assertEquals(player.getRoboticonsOwned(), roboticonsOwned);
			assertEquals(player.getScore(), score);

			// Create new cases for setters
			score = rand.nextInt(RANDOM_INT_LIMIT);
			ore = rand.nextInt(RANDOM_INT_LIMIT);
			food = rand.nextInt(RANDOM_INT_LIMIT);
			energy = rand.nextInt(RANDOM_INT_LIMIT);
			money = rand.nextInt(RANDOM_INT_LIMIT);
			roboticonsOwned = rand.nextInt(RANDOM_INT_LIMIT);
			playerNumber = 1 + rand.nextInt(1);

			// Set them
			player.setScore(score);
			player.setOre(ore);
			player.setFood(food);
			player.setEnergy(energy);
			;
			player.setMoney(money);
			player.setRoboticonsOwned(roboticonsOwned);
			player.setPlayerNumber(playerNumber);

			// Test them
			assertEquals(player.getEnergy(), energy);
			assertEquals(player.getFood(), food);
			assertEquals(player.getMoney(), money);
			assertEquals(player.getOre(), ore);
			assertEquals(player.getPlayerNumber(), playerNumber);
			assertEquals(player.getRoboticonsOwned(), roboticonsOwned);
			assertEquals(player.getScore(), score);

			// Create new cases for delta functions, making sure not to overflow
			scoreD = rand.nextInt(RANDOM_INT_LIMIT - score);
			oreD = rand.nextInt(RANDOM_INT_LIMIT - ore);
			foodD = rand.nextInt(RANDOM_INT_LIMIT - food);
			energyD = rand.nextInt(RANDOM_INT_LIMIT - energy);
			moneyD = rand.nextInt(RANDOM_INT_LIMIT - money);
			roboticonsOwnedD = rand.nextInt(RANDOM_INT_LIMIT - roboticonsOwnedD);

			// Set them
			player.changeScore(scoreD);
			player.changeOre(oreD);
			player.changeFood(foodD);
			;
			player.changeEnergy(energyD);
			player.changeMoney(moneyD);
			player.changeRoboticonsOwned(roboticonsOwnedD);

			// Test them
			assertEquals(player.getEnergy(), energy + energyD);
			assertEquals(player.getFood(), food + foodD);
			assertEquals(player.getMoney(), money + moneyD);
			assertEquals(player.getOre(), ore + oreD);
			assertEquals(player.getRoboticonsOwned(), roboticonsOwned + roboticonsOwnedD);
			assertEquals(player.getScore(), score + scoreD);

			// Now do it backwards
			player.changeScore(-scoreD);
			player.changeOre(-oreD);
			player.changeFood(-foodD);
			;
			player.changeEnergy(-energyD);
			player.changeMoney(-moneyD);
			player.changeRoboticonsOwned(-roboticonsOwnedD);

			// And test finally
			assertEquals(player.getEnergy(), energy);
			assertEquals(player.getFood(), food);
			assertEquals(player.getMoney(), money);
			assertEquals(player.getOre(), ore);
			assertEquals(player.getRoboticonsOwned(), roboticonsOwned);
			assertEquals(player.getScore(), score);

			// TODO test all the other functions
		}
	}

}
