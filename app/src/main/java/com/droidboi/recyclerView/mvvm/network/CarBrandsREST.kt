package com.droidboi.recyclerView.mvvm.network

import com.droidboi.recyclerView.mvvm.model.CarBrand

import com.squareup.moshi.Json

data class CarBrandsResponse(
	@field: Json(name = "status") val status: String,
	@field: Json(name = "result") val result: CarBrandsResult
)

data class CarBrandsResult(
	@field: Json(name = "brands") val brands: List<CarBrand>,
	@field: Json(name = "page_number") val pageNumber: Int,
	@field: Json(name = "total_pages") val totalPages: Int
)
