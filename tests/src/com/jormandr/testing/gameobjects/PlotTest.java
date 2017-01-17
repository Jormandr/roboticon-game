package com.jormandr.testing.gameobjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import de.tomgrill.gdxtesting.GdxTestRunner;

import com.badlogic.gdx.Gdx;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class PlotTest {
	
	private Random rand = new Random();
	
	// The justification for having this here is in case the original function is optimised, it can be tested against this 'safe' copy
	private int calculateValue(int value, float buff, float debuff) {
		return (int) (buff * debuff * value + 0.5f);
	}
	
	// Allow margin of error for rounding
	private boolean floatEq(float x, float y) {
		return Math.abs(x - y) < 1;
	}
	
	@Test
	public void mainPlotTest() {
		float oreBuff = 1.0f;
		float oreDebuff = 1.0f;
		float foodBuff = 1.0f;
		float foodDebuff = 1.0f;
		float energyBuff = 1.0f;
		float energyDebuff = 1.0f;
		
		int i = rand.nextInt(255);
		int j = rand.nextInt(255);
		int oreValue = rand.nextInt(255);
		int foodValue = rand.nextInt(255);
		int energyValue = rand.nextInt(255);
		TileType tileType = TileType.values()[rand.nextInt(4)];
		float [] verts = new float[8];
		
		Plot plot = new Plot(i, j, oreValue, foodValue, energyValue, tileType, verts);
		
		assertEquals(plot.getType(), tileType);
		
		assertTrue(floatEq(plot.getI(), i));
		assertTrue(floatEq(plot.getJ(), j));
		
		assertTrue(floatEq(plot.getOreValue(), calculateValue(oreValue, oreBuff, oreDebuff)));
		assertTrue(floatEq(plot.getEnergyValue(), calculateValue(energyValue, energyBuff, energyDebuff)));
		assertTrue(floatEq(plot.getFoodValue(), calculateValue(foodValue, foodBuff, foodDebuff)));
		
		// TODO test random buffs
		// TODO put this in a loop that tests at least 5 times with new values
	}

}
