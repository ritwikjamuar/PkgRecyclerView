package com.droidboi.recyclerView.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.droidboi.recyclerView.mvvm.model.MenuOption

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.*

/**
 * [ViewModel] of [com.droidboi.recyclerView.ui.activity.MainActivity].
 *
 * @param model Instance of [MainModel] as the UI Data
 *   of [com.droidboi.recyclerView.ui.activity.MainActivity].
 * @param repository Instance of [CommonRepository] as the Data Provider.
 * @author Ritwik Jamuar
 */
class MainViewModel(
	private val model: MainModel,
	private val repository: CommonRepository
) : ViewModel() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * [MutableLiveData] to propagate any change in the [model].
	 */
	private val _uiLiveData: MutableLiveData<MainModel> by lazy {
		MutableLiveData()
	}

	/**
	 * Immutable [LiveData] that is used to observe the changes in the [model].
	 */
	val uiLiveData: LiveData<MainModel>
		get() = _uiLiveData

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Handles the event when the UI is visible.
	 */
	fun onUIStarted() {
		model.menuOptions = repository.provideOptions() // Populate the List of Menu Options.
		notifyActionInUI(ACTION_POPULATE_MENU_OPTIONS) // Notify the UI to update the View.
	}

	/**
	 * Handles the event when a Menu Option is selected by the User.
	 *
	 * @param option Instance of [MenuOption] as the selection.
	 */
	fun onOptionSelected(option: MenuOption) = when (option.id) {

		1 -> notifyActionInUI(ACTION_NAVIGATE_TO_DEMO_1)

		2 -> notifyActionInUI(ACTION_NAVIGATE_TO_DEMO_2)

		3 -> notifyActionInUI(ACTION_NAVIGATE_TO_DEMO_3)

		4 -> notifyActionInUI(ACTION_NAVIGATE_TO_DEMO_4)

		else -> Unit

	}

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
