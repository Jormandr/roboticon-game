package com.jormandr.gameobjects;

/**
 * Holds a base value, buff, and debuff for a resource, and can then combine
 * these into a value. The value result is cached automatically to prevent
 * expensive FP maths
 *
 */
public class ResourceTriple {
	private float baseValue = 1.0f;
	private float buff = 1.0f;
	private float debuff = 1.0f;
	private int valueCache;

	/**
	 * Initialise and set the base value of this plot. This is currently
	 * immutable
	 * 
	 * @param baseValue
	 */
	public ResourceTriple(float baseValue) {
		this.baseValue = baseValue;
		updateValueCache();
	}

	/**
	 * Get the buff, a floating point multiplier of value, intended to be >=1.0f
	 * 
	 * @return
	 */
	public float getBuff() {
		return buff;
	}

	/**
	 * Set the buff, a floating point multiplier of value, intended to be >=1.0f
	 * 
	 * @param buff
	 */
	public void setBuff(float buff) {
		this.buff = buff;
		updateValueCache();
	}

	/**
	 * Multiply the buff, a floating point multiplier of value, intended to be
	 * >=1.0f
	 * 
	 * @param buff
	 */
	public void changeBuff(float buff) {
		this.buff *= buff;
		updateValueCache();
	}

	/**
	 * Get the debuff, a floating point multiplier of value, intended to be
	 * <=1.0f
	 * 
	 * @return
	 */
	public float getDebuff() {
		return debuff;
	}

	/**
	 * Set the debuff, a floating point multiplier of value, intended to be
	 * <=1.0f
	 * 
	 * @param debuff
	 */
	public void setDebuff(float debuff) {
		this.debuff = debuff;
		updateValueCache();
	}

	/**
	 * Multiply the debuff, a floating point multiplier of value, intended to be
	 * >=1.0f
	 * 
	 * @param debuff
	 */
	public void changeDebuff(float debuff) {
		this.debuff *= debuff;
		updateValueCache();
	}

	/**
	 * Get the plot value - the product of base, buff, and debuff, rounded up.
	 * This value is cached for performance
	 * 
	 * @return
	 */
	public int getValue() {
		// See updateValueCache for the actual function
		return valueCache;
	}

	private void updateValueCache() {
		valueCache = (int) (buff * debuff * baseValue + 0.5f);
	}

}
