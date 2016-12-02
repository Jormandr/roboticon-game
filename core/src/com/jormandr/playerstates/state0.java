package com.jormandr.playerstates;

import com.jormandr.gameobjects.MapTile;
import com.jormandr.helpers.CollisionHandler;

public class state0 {

	public static void test() { // mousedovertile method
		if (CollisionHandler.tileMouseOver()) {
			MapTile myTile = CollisionHandler.getNearestMapTile();
		}

	}
}
