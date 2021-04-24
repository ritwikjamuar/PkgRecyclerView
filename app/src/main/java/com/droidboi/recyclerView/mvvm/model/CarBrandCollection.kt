package com.droidboi.recyclerView.mvvm.model

/**
 * Sealed Class to encapsulate different types of Data to be rendered
 * while showing the Car Brand Details.
 *
 * @author Ritwik Jamuar
 */
sealed class CarBrandCollection {

	/**
	 * Data Class to represent the Basic Information of a Car Brand.
	 *
	 * @param name [String] as the name of the Car Brand,
	 * @param imageURL [String] as the URL of the Car Brand Image.
	 * @param founded [Int] as the year at which the Car Brand is founded.
	 * @param headQuarter [String] as the Headquarter Location of the Car Brand.
	 * @author Ritwik Jamuar
	 */
	data class BasicInfo(
		val name: String,
		val imageURL: String,
		val founded: Int,
		val headQuarter: String
	) : CarBrandCollection()

	/**
	 * Data Class to represent the Founder of the Car Brand.
	 *
	 * @param name [String] as the name of the Founder.
	 * @author Ritwik Jamuar
	 */
	data class Founder(
		val name: String
	) : CarBrandCollection()

	/**
	 * Data Class to represent the Popular Car of the Car Brand.
	 *
	 * @param name [String] as the Name of the Car Model.
	 * @author Ritwik Jamuar
	 */
	data class Car(
		val name: String
	) : CarBrandCollection()

	/**
	 * Data Class to represent a Loading Item to indicate the Progress.
	 *
	 * @param isLoading [Boolean] to indicate whether Loading is in progress or not.
	 * @author Ritwik Jamuar
	 */
	data class Loading(
		val isLoading: Boolean
	) : CarBrandCollection()

	/**
	 * Data Class to represent a Error Item to indicate error while fetching the data.
	 *
	 * @param description [String] as the description of the Error.
	 * @author Ritwik Jamuar
	 */
	data class Error(
		val description: String
	) : CarBrandCollection()

}
