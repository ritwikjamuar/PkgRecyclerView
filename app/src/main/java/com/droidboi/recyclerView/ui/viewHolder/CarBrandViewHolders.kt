package com.droidboi.recyclerView.ui.viewHolder

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

import androidx.core.content.ContextCompat

import com.airbnb.lottie.LottieProperty

import com.airbnb.lottie.model.KeyPath

import com.droidboi.recyclerView.R

import com.droidboi.recyclerView.databinding.*

import com.droidboi.recyclerView.mvvm.model.CarBrandCollection

import com.droidboi.recyclerView.viewHolder.BaseViewHolder

import com.squareup.picasso.Picasso

/**
 * [BaseViewHolder] to render the basic info of the Car Brand,
 *
 * @param binding Instance of [ItemCarBrandBasicInfoBinding] as the View-Accessor
 *   of this [BaseViewHolder].
 * @param picasso Instance of [Picasso] to facilitate the rendering of an Image over the Network.
 * @author Ritwik Jamuar
 */
class CarBrandBasicInfoViewHolder(
	binding: ItemCarBrandBasicInfoBinding,
	private val picasso: Picasso
) : BaseViewHolder<ItemCarBrandBasicInfoBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = Unit

	override fun cleanUp() = Unit

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Sets the Basic Info of the Car Brand.
	 *
	 * @param info Instance of [CarBrandCollection.BasicInfo] containing all the basic information.
	 */
	fun setBasicInfo(info: CarBrandCollection.BasicInfo) = with(viewAccessor) {

		valueTextCarBrandName.text = info.name

		picasso.load(info.imageURL)
			.placeholder(R.drawable.ic_launcher_foreground)
			.error(R.drawable.ic_launcher_background)
			.into(valueImageCarBrandLogo)

		valueTextCarBrandFounded.text = "${info.founded}"

		valueTextCarBrandHeadQuarter.text = info.headQuarter

	}

}

/**
 * [BaseViewHolder] to render a Loading Item for Car Brand.
 *
 * @param binding Instance of [ItemCarBrandLoadingBinding] as the View-Accessor
 *   of this [BaseViewHolder]
 * @author Ritwik Jamuar
 */
class CarBrandLoadingViewHolder(binding: ItemCarBrandLoadingBinding) :
	BaseViewHolder<ItemCarBrandLoadingBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = viewAccessor.viewLoading.addValueCallback(
		KeyPath("**"),
		LottieProperty.COLOR_FILTER,
		{
			PorterDuffColorFilter(
				ContextCompat.getColor(
					viewAccessor.root.context,
					R.color.color_text_car_brand_headquarter
				),
				PorterDuff.Mode.SRC_ATOP
			)
		}
	)

	override fun cleanUp() = Unit

}

/**
 * [BaseViewHolder] to render an individual Founder.
 *
 * @param binding Instance of [ItemCarBrandFounderBinding] as the View-Accessor
 *   of this [BaseViewHolder]
 * @author Ritwik Jamuar
 */
class CarBrandFounderViewHolder(
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
class CarBrandCarViewHolder(
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
