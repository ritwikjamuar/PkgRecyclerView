package com.droidboi.recyclerView.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.Demonstration2Model

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
) {

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
