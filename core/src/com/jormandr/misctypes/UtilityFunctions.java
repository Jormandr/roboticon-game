package com.jormandr.misctypes;

/**
 * Classes containing useful methods that could be found all over the project
 *
 */
public class UtilityFunctions {

	public final static float FLOAT_ERROR_TOLERANCE = 0.1f;

	/**
	 * checks for equality between two floats
	 * usually when checking for equality between floats high chance of returning false
	 * this gives a higher range of error allowing for more consistent checking
	 * 
	 * @param x
	 * @param y
	 * @return if two floats are equal
	 */
	public static boolean floatEq(float x, float y) {
		return Math.abs(x - y) < FLOAT_ERROR_TOLERANCE;
	}

}
