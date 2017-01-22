package com.jormandr.misctypes;

/**
 * For when lists are too hacky but you don't want to have to use the floats of
 * GDX's vectors Since this is literally just to bind 2 values, there are no
 * getters or setters, just publics
 *
 * @param <X>
 *            The type of the first value
 * @param <Y>
 *            The type of the second value
 */
public class Pair<X, Y> {
	public X x;
	public Y y;

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
}
