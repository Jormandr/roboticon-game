package com.jormandr.testing.gameobjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import de.tomgrill.gdxtesting.GdxTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class PlotTest {

	private final int ITERATIONS = 5;
	private final int RANDOM_INT_LIMIT = 255;
	private final float FLOAT_ERROR_TOLERANCE = 1.0f;

	private Random rand = new Random();

	private int i, j, oreValue, foodValue, energyValue;
	private TileType tileType;
	private float[] verts;
	private float energyBuff, energyDebuff, oreBuff, oreDebuff, foodBuff, foodDebuff;

	// The justification for having this here is in case the original function
	// is optimised, it can be tested against this 'safe' copy
	private int calculateValue(int value, float buff, float debuff) {
		return (int) (buff * debuff * value + 0.5f);
	}

	// Allow margin of error for rounding
	private boolean floatEq(float x, float y) {
		return Math.abs(x - y) < FLOAT_ERROR_TOLERANCE;
	}

	@Test
	public void mainPlotTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			// Create random test cases
			i = rand.nextInt(RANDOM_INT_LIMIT);
			j = rand.nextInt(RANDOM_INT_LIMIT);
			oreValue = rand.nextInt(RANDOM_INT_LIMIT);
			foodValue = rand.nextInt(RANDOM_INT_LIMIT);
			energyValue = rand.nextInt(RANDOM_INT_LIMIT);
			tileType = TileType.values()[rand.nextInt(TileType.values().length)];
			verts = new float[8];
			energyBuff = rand.nextFloat();
			energyDebuff = rand.nextFloat();
			oreBuff = rand.nextFloat();
			oreDebuff = rand.nextFloat();
			foodBuff = rand.nextFloat();
			foodDebuff = rand.nextFloat();

			// Set them
			Plot plot = new Plot(i, j, oreValue, foodValue, energyValue, tileType, verts);
			plot.setEnergyBuff(energyBuff);
			plot.setEnergyDebuff(energyDebuff);
			plot.setFoodBuff(foodBuff);
			plot.setFoodDebuff(foodDebuff);
			plot.setOreBuff(oreBuff);
			plot.setOreDebuff(oreDebuff);

			// Test them
			assertEquals(plot.getType(), tileType);
			assertTrue(floatEq(plot.getI(), i));
			assertTrue(floatEq(plot.getJ(), j));
			assertTrue(floatEq(plot.getOreValue(), calculateValue(oreValue, oreBuff, oreDebuff)));
			assertTrue(floatEq(plot.getEnergyValue(), calculateValue(energyValue, energyBuff, energyDebuff)));
			assertTrue(floatEq(plot.getFoodValue(), calculateValue(foodValue, foodBuff, foodDebuff)));
			assertTrue(floatEq(plot.getEnergyBuff(), energyBuff));
			assertTrue(floatEq(plot.getEnergyDebuff(), energyDebuff));
			assertTrue(floatEq(plot.getFoodBuff(), foodBuff));
			assertTrue(floatEq(plot.getFoodDebuff(), foodDebuff));
			assertTrue(floatEq(plot.getOreBuff(), oreBuff));
			assertTrue(floatEq(plot.getOreDebuff(), oreDebuff));

			// TODO test getVerts() - make sure to test by value not pointers
			// TODO think of some invariants to test convertToX/Y() by
			// TODO test hasRoboticon() vigorously
		}
	}

}
