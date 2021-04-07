package com.droidboi.recyclerView.ui.activity

import android.os.Bundle

import android.view.LayoutInflater

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager

import com.droidboi.recyclerView.databinding.ActivityMainBinding

import com.droidboi.recyclerView.mvvm.uiData.ACTION_NONE
import com.droidboi.recyclerView.mvvm.uiData.ACTION_POPULATE_MENU_OPTIONS
import com.droidboi.recyclerView.mvvm.uiData.MainModel

import com.droidboi.recyclerView.mvvm.viewModel.MainViewModel

import com.droidboi.recyclerView.mvvm.vmFactory.MainViewModelFactory

import com.droidboi.recyclerView.ui.adapter.OptionAdapter

import com.droidboi.recyclerView.utility.addItems
import com.droidboi.recyclerView.utility.cleanUp
import com.droidboi.recyclerView.utility.initialize

/**
 * [AppCompatActivity] to render the Menu Options to demonstrate the Library.
 *
 * @author Ritwik Jamuar
 */
class MainActivity : AppCompatActivity() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * Reference of [ActivityMainBinding] to access the Views of this [android.app.Activity].
	 */
	private lateinit var binding: ActivityMainBinding

	/**
	 * Reference of [MainViewModel] to notify actions and observe the changes.
	 */
	private lateinit var viewModel: MainViewModel

	/*---------------------------------------- Observers -----------------------------------------*/

	/**
	 * [Observer] of [MainViewModel.uiLiveData].
	 */
	private val uiDataObserver: Observer<MainModel> = Observer { model ->
		onUIDataChanged(model)
	}

	/*------------------------------------ Activity Callbacks ------------------------------------*/

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initializeComponents()
		setContentView(binding.root)
		setUpViews()
		attachObservers()
	}

	override fun onStart() {
		super.onStart()
		viewModel.onUIStarted()
	}

	override fun onDestroy() {
		super.onDestroy()
		cleanUpViews()
	}

	/*-------------------------------------- Private Methods -------------------------------------*/

	/**
	 * Initializes all the components of this [android.app.Activity].
	 */
	private fun initializeComponents() {
		binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
		viewModel = ViewModelProvider(
			this,
			MainViewModelFactory(this)
		).get(MainViewModel::class.java)
	}

	/**
	 * Sets-up the views under [binding].
	 */
	private fun setUpViews() = with(binding) {
		listOptions.initialize(
			OptionAdapter { option -> viewModel.onOptionSelected(option) },
			LinearLayoutManager(this@MainActivity)
		)
	}

	/**
	 * Attaches the observers from [viewModel] and else-where.
	 */
	private fun attachObservers() = with(viewModel) {
		uiLiveData.observe(this@MainActivity, uiDataObserver)
	}

	/**
	 * Handles the action propagated by the [viewModel].
	 *
	 * @param model Instance of [MainModel] encapsulating the action to be performed.
	 */
	private fun onUIDataChanged(model: MainModel) = when (model.action) {

		ACTION_NONE -> Unit

		ACTION_POPULATE_MENU_OPTIONS -> {
			binding.listOptions.addItems(model.menuOptions, true)
		}

		else -> Unit

	}

	/**
	 * Clears any references of [android.view.View] under [binding].
	 */
	private fun cleanUpViews() = with(binding) {
		listOptions.cleanUp()
	}

}
