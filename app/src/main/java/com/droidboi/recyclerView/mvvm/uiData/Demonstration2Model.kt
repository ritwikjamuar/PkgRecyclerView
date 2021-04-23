package com.droidboi.recyclerView.mvvm.uiData

import com.droidboi.recyclerView.mvvm.model.CarBrand

import java.util.*

/**
 * UI Data Model for [com.droidboi.recyclerView.ui.activity.Demonstration1Activity].
 *
 * @param action [Int] denoting an Action to be performed in the UI.
 * @param recentlyPopulatedBrands [List] of [CarBrand] as the collection of Car Brands
 *   which is fetched recently.
 * @param currentPage [Int] denoting the Page Number which is to be fetched.
 * @param errorDescription [String] denoting the description of the Error.
 * @param allBrands [List] of [CarBrand] as the collection of Car Brands which are fetched.
 * @author Ritwik Jamuar
 */
data class Demonstration2Model(
	var action: Int,
	var recentlyPopulatedBrands: List<CarBrand>?,
	var currentPage: Int = 0,
	var errorDescription: String = "",
	val allBrands: LinkedList<CarBrand> = LinkedList()
) {

	/**
	 * Checks whether all the Pages are fetched or not.
	 *
	 * @return true, if all the pages are fetched, else false.
	 */
	fun isAllPagesFetched() = 9 < currentPage

}
