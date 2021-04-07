package com.droidboi.recyclerView.mvvm.model

import java.lang.StringBuilder

/**
 * Data Class representing an individual Super Hero.
 *
 * @param id [Int] denoting the ID.
 * @param name [String] denoting the Super Hero Name.
 * @param power [List] of [String] denoting the collection of Powers this Super Hero exhibit.
 * @author Ritwik Jamuar
 */
data class SuperHero(
	val id: Int,
	val name: String,
	val power: List<String>
) {

	/**
	 * Provides the Powers of a Super Hero, collated in a String.
	 *
	 * @return [String] denoting all the Powers separated by a Comma(,).
	 */
	fun getPowers(): String = with(power) {

		// Halt the further execution if the 'power' is empty.
		if (this.isEmpty()) {
			return@with ""
		}

		val result = StringBuilder() // Create a StringBuilder that will append all the powers in it.

		// Iterate over all the powers.
		for (i in indices) {
			result.append(this[i]).append(
				if (i == this.size - 1) {
					""
				} else {
					","
				}
			)
		}

		// Return the collated String.
		result.toString()

	}

}
