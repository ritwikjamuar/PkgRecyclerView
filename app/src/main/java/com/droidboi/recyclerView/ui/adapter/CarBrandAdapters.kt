package com.droidboi.recyclerView.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import com.droidboi.recyclerView.adapter.BaseSingleVHAdapter

import com.droidboi.recyclerView.databinding.ItemCarBrandBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandFounderBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandPopularCarBinding

import com.droidboi.recyclerView.mvvm.model.CarBrand

import com.droidboi.recyclerView.ui.viewHolder.CarBrandViewHolder
import com.droidboi.recyclerView.ui.viewHolder.FounderViewHolder
import com.droidboi.recyclerView.ui.viewHolder.PopularCarViewHolder

import com.squareup.picasso.Picasso

/**
 * [BaseSingleVHAdapter] to render [CarBrand] in the [CarBrandViewHolder].
 *
 * @param picasso Instance of [Picasso] to facilitate the loading of an Image over the Network.
 * @author Ritwik Jamuar
 */
class CarBrandAdapter(private val picasso: Picasso) :
	BaseSingleVHAdapter<CarBrand, CarBrandViewHolder>() {

	/*------------------------------ BaseSingleVHAdapter Callbacks -------------------------------*/

	override fun provideViewHolder(parent: ViewGroup): CarBrandViewHolder = CarBrandViewHolder(
		ItemCarBrandBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		),
		picasso
	)

	override fun onBind(viewHolder: CarBrandViewHolder, position: Int) = with(viewHolder) {
		setCarBrand(list[position])
	}

}

/**
 * [BaseSingleVHAdapter] to render [String] denoting the Founder Name in the [FounderViewHolder].
 *
 * @author Ritwik Jamuar
 */
class FounderAdapter : BaseSingleVHAdapter<String, FounderViewHolder>() {

	/*------------------------------ BaseSingleVHAdapter Callbacks -------------------------------*/

	override fun provideViewHolder(parent: ViewGroup): FounderViewHolder = FounderViewHolder(
		ItemCarBrandFounderBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
	)

	override fun onBind(viewHolder: FounderViewHolder, position: Int) = with(viewHolder) {
		setFounder(list[position])
	}

}

/**
 * [BaseSingleVHAdapter] to render [String] denoting the Popular Car in the [PopularCarViewHolder].
 *
 * @author Ritwik Jamuar
 */
class PopularCarAdapter : BaseSingleVHAdapter<String, PopularCarViewHolder>() {

	/*------------------------------ BaseSingleVHAdapter Callbacks -------------------------------*/

	override fun provideViewHolder(parent: ViewGroup): PopularCarViewHolder = PopularCarViewHolder(
		ItemCarBrandPopularCarBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
	)

	override fun onBind(viewHolder: PopularCarViewHolder, position: Int) = with(viewHolder) {
		setPopularCar(list[position])
	}

}
