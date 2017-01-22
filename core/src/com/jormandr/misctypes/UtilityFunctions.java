package com.jormandr.misctypes;

public class UtilityFunctions {
	
	public final static float FLOAT_ERROR_TOLERANCE = 0.1f;
	
	public static boolean floatEq(float x, float y) {
		return Math.abs(x - y) < FLOAT_ERROR_TOLERANCE;
	}

}
