package com.jormandr.testing.misctypes;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.misctypes.Pair;

import java.util.Random;

public class PairTest {
	
	// Integers are used as the concrete data type, but it should not matter
	
	private final int ITERATIONS = 50;
	private final int RANDOM_INT_LIMIT = 255;

	private Random rand = new Random();
	
	private Pair<Integer, Integer> pair;
	private int x, y;
	
	@Test
	public void constructorTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			x = rand.nextInt(RANDOM_INT_LIMIT);
			y = rand.nextInt(RANDOM_INT_LIMIT);
			
			pair = new Pair<Integer, Integer>(x, y);
			
			assertTrue(pair.x == x);
			assertTrue(pair.y == y);
		}
	}

}
