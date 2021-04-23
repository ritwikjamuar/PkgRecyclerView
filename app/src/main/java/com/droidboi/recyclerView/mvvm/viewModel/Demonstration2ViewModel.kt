package com.droidboi.recyclerView.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

/**
 * [ViewModel] of [com.droidboi.recyclerView.ui.activity.Demonstration2Activity].
 *
 * @param model Instance of [Demonstration2Model] as the UI Data
 *   of [com.droidboi.recyclerView.ui.activity.Demonstration2Activity].
 * @param repository Instance of [CommonRepository] as the Data Provider.
 * @author Ritwik Jamuar
 */
class Demonstration2ViewModel(
	private val model: Demonstration2Model,
	private val repository: CommonRepository
) : ViewModel() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * [MutableLiveData] to propagate any change in the [model].
	 */
	private val _uiLiveData: MutableLiveData<Demonstration2Model> by lazy {
		MutableLiveData()
	}

	/**
	 * Immutable [LiveData] that is used to observe the changes in the [model].
	 */
	val uiLiveData: LiveData<Demonstration2Model>
		get() = _uiLiveData

	/**
	 * [CoroutineScope] with [Dispatchers.IO] for performing any operation under IO Thread.
	 */
	private val ioThreadScope: CoroutineScope by lazy {
		CoroutineScope(Dispatchers.IO)
	}

	/**
	 * [CoroutineScope] with [Dispatchers.Main] for performing any operation under Main Thread.
	 */
	private val mainThreadScope: CoroutineScope by lazy {
		CoroutineScope(Dispatchers.Main)
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Handles the event when the UI is visible.
	 */
	fun onUIStarted() = if (model.allBrands.isNotEmpty()) { // Check if there is data in the 'Model;.

			// At this point, there is some data already, and thus we can directly show
			// those fetched data first.
			model.recentlyPopulatedBrands = model.allBrands
			notifyActionInUI(ACTION_POPULATE_CAR_BRANDS)

		} else {

			// At this point, there is no existing data, and thus we must fetch the new data.
			fetchCarBrands()

		}

	/**
	 * Notifies the event that scroll has happened for the Car Brands.
	 *
	 * @param visibleItemCount [Int] denoting the count of the Child Item which are visible
	 *   in the UI.
	 * @param totalItemCount [Int] denoting the total count of Items.
	 * @param firstVisibleItemPosition [Int] denoting the position of the first visible item.
	 * @param isLoading [Boolean] denoting whether Loading is in Progress or not.
	 */
	fun onScrolled(
		visibleItemCount: Int,
		totalItemCount: Int,
		firstVisibleItemPosition: Int,
		isLoading: Boolean,
		isError: Boolean
	) {

		// Halt the further execution if the Current Page is exceeding the Total Pages available.
		if (model.isAllPagesFetched()) return

		// Halt the further execution if the Loading is in progress.
		if (isLoading || isError) return

		// Check whether the User has scrolled the Car Brand to the Last or not.
		if (
			(visibleItemCount + firstVisibleItemPosition) >= totalItemCount
			&&
			firstVisibleItemPosition >= 0
		) {

			// At this point, the user has scrolled to the last item of the list.
			// So, we will fetch the Car Brands from 'repository'.
			fetchCarBrands()

		}

	}

	/**
	 * Handles the event when 'Retry' is clicked in the UI.
	 */
	fun onRetryClicked() {
		hideError()
		fetchCarBrands()
	}

	/**
	 * Handles the event when the [Demonstration2Model.recentlyPopulatedBrands] is populated
	 * in the UI.
	 */
	fun onBrandsPopulated() {
		model.recentlyPopulatedBrands = null
	}

	/*-------------------------------------- Private Methods -------------------------------------*/

	/**
	 * Performs fetching of the Car Brands from the [repository].
	 */
	private fun fetchCarBrands() {
		showLoading()
		ioThreadScope.launch { // Switch to IO Thread to perform Data Processing.
			repository.getCarBrands(model.currentPage).collect { brandResponse -> // Perform collecting the response from the repository.
					mainThreadScope.launch { // Switch back to Main Thread for rendering.
						hideLoading()
						brandResponse?.let { response ->
							model.recentlyPopulatedBrands = response.result.brands // Populate the 'recentlyPopulatedBrands' in the 'model'.
							model.allBrands.addAll(response.result.brands) // Add this recently fetched Brands into collection of All Brands.
							model.currentPage += 1 // Increase the Current Page by 1.
							notifyActionInUI(ACTION_POPULATE_CAR_BRANDS) // Notifies the UI to Render this recently fetched Car Brands.
						} ?: showError("Something went wrong") // Show Error if the Response is null.
					}
				}
		}
	}

	/**
	 * Shows the Loading in the UI.
	 */
	private fun showLoading() = notifyActionInUI(ACTION_SHOW_LOADING_ON_LIST)

	/**
	 * Hides the Loading from the UI.
	 */
	private fun hideLoading() = notifyActionInUI(ACTION_HIDE_LOADING_ON_LIST)

	/**
	 * Shows the Error in the UI.
	 *
	 * @param description [String] denoting the Error Description.
	 */
	private fun showError(@Suppress("SameParameterValue") description: String) {
		model.errorDescription = description
		notifyActionInUI(ACTION_SHOW_ERROR_ON_LIST)
	}

	/**
	 * Hides the Error from the UI.
	 */
	private fun hideError() = notifyActionInUI(ACTION_HIDE_ERROR_ON_LIST)

	/**
	 * Notifies any [action] as an update in the [model] to the UI.
	 *
	 * @param action [Int] denoting an action to be performed in the UI.
	 */
	private fun notifyActionInUI(action: Int) {
		model.action = action
		notifyUpdateInUI()
	}

	/**
	 * Notifies any update in [model] via [_uiLiveData].
	 */
	private fun notifyUpdateInUI() = with(_uiLiveData) {
		value = model
	}

}
