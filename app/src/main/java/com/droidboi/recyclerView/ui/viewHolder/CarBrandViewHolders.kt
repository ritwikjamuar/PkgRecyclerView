package com.droidboi.recyclerView.ui.viewHolder

import androidx.recyclerview.widget.LinearLayoutManager

import com.droidboi.recyclerView.R

import com.droidboi.recyclerView.databinding.ItemCarBrandBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandFounderBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandPopularCarBinding

import com.droidboi.recyclerView.mvvm.model.CarBrand

import com.droidboi.recyclerView.ui.adapter.FounderAdapter
import com.droidboi.recyclerView.ui.adapter.PopularCarAdapter

import com.droidboi.recyclerView.utility.addItems
import com.droidboi.recyclerView.utility.cleanUp
import com.droidboi.recyclerView.utility.initialize

import com.droidboi.recyclerView.viewHolder.BaseViewHolder

import com.squareup.picasso.Picasso

/**
 * [BaseViewHolder] to render an individual [CarBrand].
 *
 * @param binding Instance of [ItemCarBrandFounderBinding] as the View-Accessor
 *   of this [BaseViewHolder].
 * @param picasso Instance of [Picasso] to facilitate the rendering of an Image over the Network.
 * @author Ritwik Jamuar
 */
class CarBrandViewHolder(
	binding: ItemCarBrandBinding,
	private val picasso: Picasso,
) : BaseViewHolder<ItemCarBrandBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = Unit

	override fun cleanUp() = with(viewAccessor) {
		listCarBrandFounders.cleanUp()
		listCarBrandPopularCars.cleanUp()
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Set the [CarBrand].
	 *
	 * @param brand Instance of [CarBrand] denoting the Car Brand details.
	 */
	fun setCarBrand(brand: CarBrand) : Unit = with(viewAccessor) {

		valueTextCarBrandName.text = brand.name

		picasso.load(brand.brandImageURL)
			.placeholder(R.drawable.ic_launcher_foreground)
			.error(R.drawable.ic_launcher_background)
			.into(valueImageCarBrandLogo)

		valueTextCarBrandFounded.text = "${brand.founded}"

		valueTextCarBrandHeadQuarter.text = brand.headQuarters

		with(listCarBrandFounders) {
			initialize(
				FounderAdapter(),
				LinearLayoutManager(listCarBrandFounders.context).apply {
					initialPrefetchItemCount = brand.founders.size
				}
			)
			post {
				addItems(brand.founders, true)
			}
		}

		with(listCarBrandPopularCars) {
			initialize(
				PopularCarAdapter(),
				LinearLayoutManager(listCarBrandFounders.context).apply {
					initialPrefetchItemCount = brand.founders.size
				}
			)
			post {
				addItems(brand.popularCars, true)
			}
		}

	}

}

/**
 * [BaseViewHolder] to render an individual Founder.
 *
 * @param binding Instance of [ItemCarBrandFounderBinding] as the View-Accessor
 *   of this [BaseViewHolder]
 * @author Ritwik Jamuar
 */
class FounderViewHolder(
	binding: ItemCarBrandFounderBinding
) : BaseViewHolder<ItemCarBrandFounderBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = Unit

	override fun cleanUp() = Unit

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Set the name of the Founder Name of the Brand.
	 *
	 * @param founder [String] denoting the name of the Founder.
	 */
	fun setFounder(founder: String) = with(viewAccessor.valueTextCarBrandFounder) {
		text = founder
	}

}

/**
 * [BaseViewHolder] to render an individual Popular Car.
 *
 * @param binding Instance of [ItemCarBrandPopularCarBinding] as the View-Accessor
 *   of this [BaseViewHolder]
 * @author Ritwik Jamuar
 */
class PopularCarViewHolder(
	binding: ItemCarBrandPopularCarBinding
) : BaseViewHolder<ItemCarBrandPopularCarBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = Unit

	override fun cleanUp() = Unit

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Set the name of the Popular Car Name of the Brand.
	 *
	 * @param car [String] denoting the name of the Car.
	 */
	fun setPopularCar(car: String) = with(viewAccessor.valueTextCarBrandPopularCar) {
		text = car
	}

}
