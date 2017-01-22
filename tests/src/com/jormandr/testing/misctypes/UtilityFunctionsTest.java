package com.jormandr.testing.misctypes;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.misctypes.UtilityFunctions;

import java.util.Random;

public class UtilityFunctionsTest {
	
	private final int ITERATIONS = 50;
	private final float tolerance = UtilityFunctions.FLOAT_ERROR_TOLERANCE;
	
	private Random rand = new Random();
	
	private float x, y;
	
	@Test
	public void floatEqTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			x = rand.nextFloat();
			y = x;
			assertTrue(UtilityFunctions.floatEq(x, y));
			y += tolerance - 0.00001f;
			assertTrue(UtilityFunctions.floatEq(x, y));
			y += 0.0001f;
			assertTrue(!UtilityFunctions.floatEq(x, y));
			y = x - tolerance + 0.00001f;
			assertTrue(UtilityFunctions.floatEq(x, y));
			y -= 0.0001f;
			assertTrue(!UtilityFunctions.floatEq(x, y));
		}		
	}

}
