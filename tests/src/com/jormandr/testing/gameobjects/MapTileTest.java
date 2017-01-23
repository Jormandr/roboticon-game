package com.jormandr.testing.gameobjects;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import static com.jormandr.misctypes.UtilityFunctions.floatEq; // Don't worry, it's tested too
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;

public class MapTileTest {

	private final float originOffsetX = 2.0f;
	private final float originOffsetY = 4.0f;
	private final float ww = (float) GameConfig.getHalfTileWidth();
	private final float hh = (float) GameConfig.getHalfTileHeight();;
	private final float tw = GameConfig.getWidth() / (ww * originOffsetX);
	private final float th = GameConfig.getHeight() / (hh * originOffsetY);

	// Non-static methods can't be tested here since its abstract
	@Test
	public void staticTests() {
		assertTrue(floatEq(MapTile.getTH(), th));
		assertTrue(floatEq(MapTile.getTW(), tw));
	}

}
