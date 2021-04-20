package com.droidboi.recyclerView.mvvm.uiData

import com.droidboi.recyclerView.mvvm.model.CarBrand

/**
 * UI Data Model for [com.droidboi.recyclerView.ui.activity.Demonstration1Activity].
 *
 * @param action [Int] denoting an Action to be performed in the UI.
 * @param recentlyPopulatedBrands [List] of [CarBrand] as the collection of Car Brands
 *   which is fetched recently.
 * @param currentPage [Int] denoting the Page Number which is to be fetched.
 * @author Ritwik Jamuar
 */
data class Demonstration2Model(
	var action: Int,
	var recentlyPopulatedBrands: List<CarBrand>?,
	var currentPage: Int = 0
)