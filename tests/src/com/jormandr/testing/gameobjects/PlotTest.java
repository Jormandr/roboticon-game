package com.jormandr.testing.gameobjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import de.tomgrill.gdxtesting.GdxTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import static com.jormandr.misctypes.UtilityFunctions.floatEq;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class PlotTest {

	private final int ITERATIONS = 50;
	private final int RANDOM_INT_LIMIT = 255;

	private Random rand = new Random();

	private Plot plot;
	private int i, j, oreValue, foodValue, energyValue;
	private TileType tileType;
	private float energyBuff, energyDebuff, oreBuff, oreDebuff, foodBuff, foodDebuff;
	private float energyBuffDelta, energyDebuffDelta, oreBuffDelta, oreDebuffDelta, foodBuffDelta, foodDebuffDelta;

	// The justification for having this here is in case the original function
	// is optimised, it can be tested against this 'safe' copy
	private int calculateValue(int value, float buff, float debuff) {
		return (int) (buff * debuff * value + 0.5f);
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
			energyBuff = rand.nextFloat();
			energyDebuff = rand.nextFloat();
			oreBuff = rand.nextFloat();
			oreDebuff = rand.nextFloat();
			foodBuff = rand.nextFloat();
			foodDebuff = rand.nextFloat();

			// Set them
			plot = new Plot(i, j, oreValue, foodValue, energyValue, tileType);
			assertTrue(!plot.hasRoboticon());
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
			
			// Test buff modifiers
			// Generate deltas
			energyBuffDelta = rand.nextFloat();
			energyDebuffDelta = rand.nextFloat();
			foodBuffDelta = rand.nextFloat();
			foodDebuffDelta = rand.nextFloat();
			oreBuffDelta = rand.nextFloat();
			oreDebuffDelta = rand.nextFloat();
			// Apply here
			energyBuff += energyBuffDelta;
			energyDebuff += energyDebuffDelta;
			foodBuff += foodBuffDelta;
			foodDebuff += foodDebuffDelta;
			oreBuff += oreBuffDelta;
			oreDebuff += oreDebuffDelta;
			// Apply to plot
			plot.changeEnergyBuff(energyBuffDelta);
			plot.changeEnergyDebuff(energyDebuffDelta);
			plot.changeFoodBuff(foodBuffDelta);
			plot.changeFoodDebuff(foodDebuffDelta);
			plot.changeOreBuff(oreBuffDelta);
			plot.changeOreDebuff(oreDebuffDelta);
			// Test
			assertTrue(floatEq(plot.getOreValue(), calculateValue(oreValue, oreBuff, oreDebuff)));
			assertTrue(floatEq(plot.getEnergyValue(), calculateValue(energyValue, energyBuff, energyDebuff)));
			assertTrue(floatEq(plot.getFoodValue(), calculateValue(foodValue, foodBuff, foodDebuff)));
			assertTrue(floatEq(plot.getEnergyBuff(), energyBuff));
			assertTrue(floatEq(plot.getEnergyDebuff(), energyDebuff));
			assertTrue(floatEq(plot.getFoodBuff(), foodBuff));
			assertTrue(floatEq(plot.getFoodDebuff(), foodDebuff));
			assertTrue(floatEq(plot.getOreBuff(), oreBuff));
			assertTrue(floatEq(plot.getOreDebuff(), oreDebuff));
			
			// Test hasRoboticon()
			// Reset to no roboticon
			plot.removeRoboticon();
			assertTrue(!plot.hasRoboticon());
			// First using mutations manually
			// Energy
			plot.setEnergyBuff(2 + rand.nextFloat());
			assertTrue(plot.hasRoboticon());
			plot.setEnergyBuff(1.0f);
			assertTrue(!plot.hasRoboticon());
			// Food
			plot.setFoodBuff(2 + rand.nextFloat());
			assertTrue(plot.hasRoboticon());
			plot.setFoodBuff(1.0f);
			assertTrue(!plot.hasRoboticon());
			// Ore
			plot.setOreBuff(2 + rand.nextFloat());
			assertTrue(plot.hasRoboticon());
			plot.setOreBuff(1.0f);
			assertTrue(!plot.hasRoboticon());
			// Now test the quick reset function
			plot.setEnergyBuff(2 + rand.nextFloat());
			plot.setFoodBuff(2 + rand.nextFloat());
			plot.setOreBuff(2 + rand.nextFloat());
			assertTrue(plot.hasRoboticon());
			plot.removeRoboticon();
			assertTrue(!plot.hasRoboticon());
			assertTrue(floatEq(plot.getEnergyBuff(), 1.0f));
			assertTrue(floatEq(plot.getFoodBuff(), 1.0f));
			assertTrue(floatEq(plot.getOreBuff(), 1.0f));

			// TODO think of some invariants to test convertToX/Y() by
		}
	}

}
