package com.droidboi.recyclerView.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.Demonstration1Model

/**
 * [ViewModel] of [com.droidboi.recyclerView.ui.activity.Demonstration1Activity].
 *
 * @param model Instance of [Demonstration1Model] as the UI Data
 *   of [com.droidboi.recyclerView.ui.activity.Demonstration1Activity].
 * @param repository Instance of [CommonRepository] as the Data Provider.
 * @author Ritwik Jamuar
 */
class Demonstration1ViewModel(
	private val model: Demonstration1Model,
	private val repository: CommonRepository
) : ViewModel() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * [MutableLiveData] to propagate any change in the [model].
	 */
	private val _uiLiveData: MutableLiveData<Demonstration1Model> by lazy {
		MutableLiveData()
	}

	/**
	 * Immutable [LiveData] that is used to observe the changes in the [model].
	 */
	val uiLiveData: LiveData<Demonstration1Model>
		get() = _uiLiveData

	/*-------------------------------------- Private Methods -------------------------------------*/

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
