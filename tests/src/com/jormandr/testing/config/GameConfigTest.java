package com.jormandr.testing.config;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.jormandr.config.GameConfig;

public class GameConfigTest {

	@Test
	public void positiveSizes() {
		assertTrue(GameConfig.getWidth() > 0);
		assertTrue(GameConfig.getHeight() > 0);
		assertTrue(GameConfig.getMapHeight() > 0);
		assertTrue(GameConfig.getMapWidth() > 0);
		assertTrue(GameConfig.getRandomEventChance() >= 0);
		assertTrue(GameConfig.getEnergyValueRandomLimit() >= 0);
		assertTrue(GameConfig.getOreValueRandomLimit() >= 0);
		assertTrue(GameConfig.getFoodValueRandomLimit() >= 0);
		assertTrue(GameConfig.getTileHeight() > 0.0f);
		assertTrue(GameConfig.getTileWidth() > 0.0f);
	}

}
