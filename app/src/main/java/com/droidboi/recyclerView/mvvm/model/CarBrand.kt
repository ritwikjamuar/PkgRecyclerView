package com.droidboi.recyclerView.mvvm.model

import com.squareup.moshi.Json

/**
 * Data Class to hold information of a particular Car Brand.
 *
 * @param id [Int] denoting ID.
 * @param name [String] denoting the name of this Car Brand.
 * @param founded [Int] denoting the Year this Car Brand formed.
 * @param brandImageURL [String] as the URL of the Car Brand Image.
 * @param headQuarters [String] denoting the Head Quarter of this Car Brand.
 * @param founders [List] of [String] containing the name of the Founders of this Car Brand.
 * @param popularCars [List] of [String] containing the List of Popular Cars under this Car Brand.
 * @author Ritwik Jamuar
 */
data class CarBrand(
	@field: Json(name = "id") val id: Int,
	@field: Json(name = "name") val name: String,
	@field: Json(name = "founded") val founded: Int,
	@field: Json(name = "brand_image_url") val brandImageURL: String,
	@field: Json(name = "head_quarters") val headQuarters: String,
	@field: Json(name = "founders") val founders: List<String>,
	@field: Json(name = "popularCars") val popularCars: List<String>
)
