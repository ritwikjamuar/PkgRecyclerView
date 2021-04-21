package com.droidboi.recyclerView.mvvm.model

/**
 * Data Class to hold information of a particular Car Brand.
 *
 * @param id [Int] denoting ID.
 * @param name [String] denoting the name of this Car Brand.
 * @param founded [Int] denoting the Year this Car Brand formed.
 * @param brand_image_url [String] as the URL of the Car Brand Image.
 * @param head_quarters [String] denoting the Head Quarter of this Car Brand.
 * @param founders [List] of [String] containing the name of the Founders of this Car Brand.
 * @param popularCars [List] of [String] containing the List of Popular Cars under this Car Brand.
 * @author Ritwik Jamuar
 */
data class CarBrand(
	val id: Int,
	val name: String,
	val founded: Int,
	val brand_image_url: String,
	val head_quarters: String,
	val founders: List<String>,
	val popularCars: List<String>
)
