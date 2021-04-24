package com.droidboi.recyclerView.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.droidboi.recyclerView.adapter.BaseMultipleVHAdapter
import com.droidboi.recyclerView.adapter.BaseSingleVHAdapter

import com.droidboi.recyclerView.databinding.*

import com.droidboi.recyclerView.mvvm.model.CarBrandCollection

import com.droidboi.recyclerView.ui.viewHolder.*

import com.squareup.picasso.Picasso

/**
 * Constant [Int] as the View-Type to render [CarBrandBasicInfoViewHolder].
 */
const val VIEW_TYPE_CAR_BRAND_BASIC_INFO : Int = 0

/**
 * Constant [Int] as the View-Type to render [CarBrandFounderViewHolder].
 */
const val VIEW_TYPE_CAR_BRAND_FOUNDER : Int = 1

/**
 * Constant [Int] as the View-Type to render [CarBrandCarViewHolder].
 */
const val VIEW_TYPE_CAR_BRAND_CAR : Int = 2

/**
 * Constant [Int] as the View-Type to render [CarBrandLoadingViewHolder].
 */
const val VIEW_TYPE_CAR_BRAND_LOADING : Int =  3

/**
 * Constant [Int] as the View-Type to render [ErrorViewHolder].
 */
const val VIEW_TYPE_ERROR : Int = 4

/**
 * [BaseSingleVHAdapter] to render [CarBrandCollection] in the various [RecyclerView.ViewHolder]s.
 *
 * @param picasso Instance of [Picasso] to facilitate the loading of an Image over the Network.
 * @param errorRetryListener Lambda Expression to intercept click on Retry in [ErrorViewHolder].
 * @author Ritwik Jamuar
 */
class CarBrandAdapter(
	private val picasso: Picasso,
	private val errorRetryListener : () -> Unit
) : BaseMultipleVHAdapter<CarBrandCollection>() {

	/*----------------------------- BaseMultipleVHAdapter Callbacks ------------------------------*/

	override fun provideViewType(position: Int): Int = when(list[position]) {
		is CarBrandCollection.Loading -> VIEW_TYPE_CAR_BRAND_LOADING
		is CarBrandCollection.Error -> VIEW_TYPE_ERROR
		is CarBrandCollection.BasicInfo -> VIEW_TYPE_CAR_BRAND_BASIC_INFO
		is CarBrandCollection.Founder -> VIEW_TYPE_CAR_BRAND_FOUNDER
		is CarBrandCollection.Car -> VIEW_TYPE_CAR_BRAND_CAR
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

			VIEW_TYPE_CAR_BRAND_FOUNDER -> CarBrandFounderViewHolder(
				ItemCarBrandFounderBinding.inflate(inflater, parent, false)
			)

			VIEW_TYPE_CAR_BRAND_CAR -> CarBrandCarViewHolder(
				ItemCarBrandPopularCarBinding.inflate(inflater, parent, false)
			)

			VIEW_TYPE_CAR_BRAND_BASIC_INFO ->  CarBrandBasicInfoViewHolder(
				ItemCarBrandBasicInfoBinding.inflate(inflater, parent, false),
				picasso
			)

			else -> CarBrandLoadingViewHolder(
				ItemCarBrandLoadingBinding.inflate(inflater, parent, false)
			)

		}

	}

	override fun onBind(viewHolder: RecyclerView.ViewHolder, position: Int) = when(viewHolder) {

		is CarBrandLoadingViewHolder -> Unit

		is ErrorViewHolder -> {
			viewHolder.setDescription((list[position] as CarBrandCollection.Error).description)
		}

		is CarBrandFounderViewHolder -> {
			viewHolder.setFounder((list[position] as CarBrandCollection.Founder).name)
		}

		is CarBrandCarViewHolder -> {
			viewHolder.setPopularCar((list[position] as CarBrandCollection.Car).name)
		}

		is CarBrandBasicInfoViewHolder -> {
			viewHolder.setBasicInfo((list[position] as CarBrandCollection.BasicInfo))
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
		return list[positionToCheck] is CarBrandCollection.Loading

	}

	/**
	 * Adds the Loading Item in the view.
	 */
	fun addLoading() {

		val pointOfInsertion = list.size // Determine the position of insertion of Loading Item.

		// Halt the further execution if the last item in the List is a Loading Item.
		if (pointOfInsertion != 0 && list[pointOfInsertion - 1] is CarBrandCollection.Loading) return

		// Add the Dummy Item representing for a Loading Item.
		list.add(CarBrandCollection.Loading(true))

		// Notify this adapter of insertion of this Loading Item.
		notifyItemInserted(pointOfInsertion)

	}

	/**
	 * Removes the Loading Item from the view.
	 */
	fun removeLoading() {

		val pointOfDeletion = list.size // Determine the position of deletion of Loading Item.

		// Halt the further execution if the last item is not a Loading Item.
		if (pointOfDeletion <= 0 || list[pointOfDeletion - 1] !is CarBrandCollection.Loading) return

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
		return list[positionToCheck] is CarBrandCollection.Error

	}

	/**
	 * Adds the Loading Item in the view.
	 */
	fun addError(description: String) {

		val pointOfInsertion = list.size // Determine the position of insertion of Error Item.

		// Halt the further execution if the last item in the List is a Error Item.
		if (pointOfInsertion != 0 && list[pointOfInsertion - 1] is CarBrandCollection.Error) return

		// Add the Dummy Item representing for a Error Item.
		list.add(CarBrandCollection.Error(description))

		// Notify this adapter of insertion of this Error Item.
		notifyItemInserted(pointOfInsertion)

	}

	/**
	 * Removes the Error Item from the view.
	 */
	fun removeError() {

		val pointOfDeletion = list.size // Determine the position of deletion of Error Item.

		// Halt the further execution if the last item is not a Error Item.
		if (pointOfDeletion <= 0 || list[pointOfDeletion - 1] !is CarBrandCollection.Error) return

		// Remove the dummy item as Error Item from the list.
		list.removeAt(pointOfDeletion - 1)

		// Notify this adapter of removal of this Error Item.
		notifyItemRemoved(pointOfDeletion)

	}

}
