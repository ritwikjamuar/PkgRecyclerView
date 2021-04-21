package com.droidboi.recyclerView.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.droidboi.recyclerView.adapter.BaseMultipleVHAdapter
import com.droidboi.recyclerView.adapter.BaseSingleVHAdapter

import com.droidboi.recyclerView.databinding.*

import com.droidboi.recyclerView.mvvm.model.CarBrand

import com.droidboi.recyclerView.ui.viewHolder.*

import com.squareup.picasso.Picasso

const val VIEW_TYPE_CAR_BRAND : Int = 0

const val VIEW_TYPE_CAR_BRAND_LOADING : Int =  1

const val VIEW_TYPE_ERROR : Int = 2

/**
 * [BaseSingleVHAdapter] to render [CarBrand] in the [CarBrandViewHolder].
 *
 * @param picasso Instance of [Picasso] to facilitate the loading of an Image over the Network.
 * @author Ritwik Jamuar
 */
class CarBrandAdapter(private val picasso: Picasso, private val errorRetryListener : () -> Unit) :
	BaseMultipleVHAdapter<CarBrand>() {

	/*----------------------------- BaseMultipleVHAdapter Callbacks ------------------------------*/

	override fun provideViewType(position: Int): Int = when(list[position].id) {
		-1 -> VIEW_TYPE_CAR_BRAND_LOADING
		-2 -> VIEW_TYPE_ERROR
		else -> VIEW_TYPE_CAR_BRAND
	}

	override fun provideViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

		val inflater = LayoutInflater.from(parent.context)

		return when(viewType) {

			VIEW_TYPE_CAR_BRAND_LOADING -> CarBrandLoadingViewHolder(
				ItemCarBrandLoadingBinding.inflate(inflater, parent, false)
			)

			VIEW_TYPE_ERROR -> ErrorViewHolder(
				ItemErrorBinding.inflate(inflater, parent, false),
				errorRetryListener
			)

			else -> CarBrandViewHolder(
				ItemCarBrandBinding.inflate(inflater, parent, false),
				picasso
			)

		}

	}

	override fun onBind(viewHolder: RecyclerView.ViewHolder, position: Int) = when(viewHolder) {

		is CarBrandLoadingViewHolder -> Unit

		is ErrorViewHolder -> {
			viewHolder.setDescription(list[position].name)
		}

		is CarBrandViewHolder -> {
			viewHolder.setCarBrand(list[position])
		}

		else -> Unit

	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Checks whether the loading is being displayed or not.
	 *
	 * @return true, if the [CarBrandLoadingViewHolder] is the last item rendered in the [list]
	 * or not.
	 */
	fun isLoading(): Boolean {

		val positionToCheck = list.size - 1 // Get the Last Item index.

		// Halt the further execution if the 'positionToCheck' is less than 0 or not
		// to avoid ArrayOutOfBoundException.
		if (positionToCheck < 0) return false

		// Return the boolean expression that verifies the last item as the Loading Item.
		return list[positionToCheck].id == -1

	}

	/**
	 * Adds the Loading Item in the view.
	 */
	fun addLoading() {

		val pointOfInsertion = list.size // Determine the position of insertion of Loading Item.

		// Halt the further execution if the last item in the List is a Loading Item.
		if (pointOfInsertion != 0 && list[pointOfInsertion - 1].id == -1) return

		// Add the Dummy Item representing for a Loading Item.
		list.add(
			CarBrand(
				-1,
				"",
				0,
				"",
				"",
				ArrayList(),
				ArrayList()
			)
		)

		// Notify this adapter of insertion of this Loading Item.
		notifyItemInserted(pointOfInsertion)

	}

	/**
	 * Removes the Loading Item from the view.
	 */
	fun removeLoading() {

		val pointOfDeletion = list.size // Determine the position of deletion of Loading Item.

		// Halt the further execution if the last item is not a Loading Item.
		if (pointOfDeletion <= 0 || list[pointOfDeletion - 1].id != -1) return

		// Remove the dummy item as Loading Item from the list.
		list.removeAt(pointOfDeletion - 1)

		// Notify this adapter of removal of this Loading Item.
		notifyItemRemoved(pointOfDeletion)

	}

	/**
	 * Checks whether the error is being displayed or not.
	 *
	 * @return true, if the [ErrorViewHolder] is the last item rendered in the [list]
	 * or not.
	 */
	fun isError(): Boolean {

		val positionToCheck = list.size - 1 // Get the Last Item index.

		// Halt the further execution if the 'positionToCheck' is less than 0 or not
		// to avoid ArrayOutOfBoundException.
		if (positionToCheck < 0) return false

		// Return the boolean expression that verifies the last item as the Error Item.
		return list[positionToCheck].id == -2

	}

	/**
	 * Adds the Loading Item in the view.
	 */
	fun addError(description: String) {

		val pointOfInsertion = list.size // Determine the position of insertion of Error Item.

		// Halt the further execution if the last item in the List is a Error Item.
		if (pointOfInsertion != 0 && list[pointOfInsertion - 1].id == -2) return

		// Add the Dummy Item representing for a Error Item.
		list.add(
			CarBrand(
				-2,
				description,
				0,
				"",
				"",
				ArrayList(),
				ArrayList()
			)
		)

		// Notify this adapter of insertion of this Error Item.
		notifyItemInserted(pointOfInsertion)

	}

	/**
	 * Removes the Error Item from the view.
	 */
	fun removeError() {

		val pointOfDeletion = list.size // Determine the position of deletion of Error Item.

		// Halt the further execution if the last item is not a Error Item.
		if (pointOfDeletion <= 0 || list[pointOfDeletion - 1].id != -2) return

		// Remove the dummy item as Error Item from the list.
		list.removeAt(pointOfDeletion - 1)

		// Notify this adapter of removal of this Error Item.
		notifyItemRemoved(pointOfDeletion)

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
