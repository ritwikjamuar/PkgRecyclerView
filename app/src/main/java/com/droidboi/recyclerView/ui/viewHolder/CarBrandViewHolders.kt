package com.droidboi.recyclerView.ui.viewHolder

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.LinearLayoutManager

import com.airbnb.lottie.LottieProperty

import com.airbnb.lottie.model.KeyPath

import com.droidboi.recyclerView.R

import com.droidboi.recyclerView.databinding.ItemCarBrandBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandFounderBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandLoadingBinding
import com.droidboi.recyclerView.databinding.ItemCarBrandPopularCarBinding

import com.droidboi.recyclerView.mvvm.model.CarBrand

import com.droidboi.recyclerView.ui.adapter.FounderAdapter
import com.droidboi.recyclerView.ui.adapter.PopularCarAdapter

import com.droidboi.recyclerView.utility.addItems
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

	override fun cleanUp() = Unit

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Set the [CarBrand].
	 *
	 * @param brand Instance of [CarBrand] denoting the Car Brand details.
	 */
	fun setCarBrand(brand: CarBrand): Unit = with(viewAccessor) {

		valueTextCarBrandName.text = brand.name

		picasso.load(brand.brand_image_url)
			.placeholder(R.drawable.ic_launcher_foreground)
			.error(R.drawable.ic_launcher_background)
			.into(valueImageCarBrandLogo)

		valueTextCarBrandFounded.text = "${brand.founded}"

		valueTextCarBrandHeadQuarter.text = brand.head_quarters

		with(listCarBrandFounders) {

			// Additional Check to ignore the re-creation of
			// RecyclerView's Adapter and LayoutManager.
			// This check is due to the reason that creating a Views under ViewHolder
			// can prove costly especially when the View is complex,
			// So we simply halt the further execution if there is already
			if (adapter != null)

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

			// Additional Check to ignore the re-creation of
			// RecyclerView's Adapter and LayoutManager.
			// This check is due to the reason that creating a Views under ViewHolder
			// can prove costly especially when the View is complex,
			// So we simply halt the further execution if there is already
			if (adapter != null) return

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
				ContextCompat.getColor(viewAccessor.root.context, R.color.color_text_car_brand_headquarter),
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
