package com.jormandr.testing.gameobjects;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.gameobjects.ResourceTriple;
import static com.jormandr.misctypes.UtilityFunctions.floatEq;

import java.util.Random;

public class ResourceTripleTest {

	private final int ITERATIONS = 50;

	private Random rand = new Random();

	private ResourceTriple triple;
	private float base, buff, debuff;
	private float buffDelta, debuffDelta;

	private int getValue(float base, float buff, float debuff) {
		return (int) (buff * debuff * base + 0.5f);
	}

	@Test
	public void mainResourceTripleTest() {
		for (int counter = 0; counter < ITERATIONS; counter++) {
			base = rand.nextFloat();
			buff = rand.nextFloat();
			debuff = rand.nextFloat();

			triple = new ResourceTriple(base);

			assertTrue(triple.getValue() == getValue(base, 1.0f, 1.0f));

			triple.setBuff(buff);
			triple.setDebuff(debuff);

			assertTrue(floatEq(triple.getBuff(), buff));
			assertTrue(floatEq(triple.getDebuff(), debuff));
			assertTrue(triple.getValue() == getValue(base, buff, debuff));

			buffDelta = rand.nextFloat();
			debuffDelta = rand.nextFloat();

			buff *= buffDelta;
			debuff += debuffDelta;

			triple.changeBuff(buffDelta);
			triple.changeDebuff(debuffDelta);

			assertTrue(floatEq(triple.getBuff(), buff));
			assertTrue(floatEq(triple.getDebuff(), debuff));
			assertTrue(triple.getValue() == getValue(base, buff, debuff));
		}
	}

}
