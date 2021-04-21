package com.droidboi.recyclerView.mvvm.network

import com.droidboi.recyclerView.mvvm.model.CarBrand

data class CarBrandsResponse(
	val status: String,
	val result: CarBrandsResult
)

data class CarBrandsResult(
	val brands: List<CarBrand>,
	val page_number: Int,
	val total_pages: Int
)
