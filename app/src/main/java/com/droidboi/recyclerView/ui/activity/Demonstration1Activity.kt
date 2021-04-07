package com.droidboi.recyclerView.ui.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.view.LayoutInflater

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager

import com.droidboi.recyclerView.databinding.ActivityDemonstration1Binding

import com.droidboi.recyclerView.mvvm.uiData.ACTION_NONE
import com.droidboi.recyclerView.mvvm.uiData.ACTION_POPULATE_SUPER_HEROES

import com.droidboi.recyclerView.mvvm.uiData.Demonstration1Model
import com.droidboi.recyclerView.mvvm.uiData.MainModel

import com.droidboi.recyclerView.mvvm.viewModel.Demonstration1ViewModel

import com.droidboi.recyclerView.mvvm.vmFactory.Demonstration1ViewModelFactory

import com.droidboi.recyclerView.ui.adapter.SuperHeroAdapter
import com.droidboi.recyclerView.utility.addItems

import com.droidboi.recyclerView.utility.cleanUp
import com.droidboi.recyclerView.utility.initialize

/**
 * [AppCompatActivity] to demonstrate the [androidx.recyclerview.widget.RecyclerView]
 * with the use-case below:
 *
 * Render a Collection of Superheroes with no click listener on each hero.
 */
class Demonstration1Activity : AppCompatActivity() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * Reference of [ActivityDemonstration1Binding] to access the Views
	 * of this [android.app.Activity].
	 */
	private lateinit var binding: ActivityDemonstration1Binding

	/**
	 * Reference of [Demonstration1ViewModel] to notify actions and observe the changes.
	 */
	private lateinit var viewModel: Demonstration1ViewModel

	/*---------------------------------------- Observers -----------------------------------------*/

	/**
	 * [Observer] of [Demonstration1ViewModel.uiLiveData].
	 */
	private val uiDataObserver: Observer<Demonstration1Model> = Observer { model ->
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
		binding = ActivityDemonstration1Binding.inflate(LayoutInflater.from(this))
		viewModel = ViewModelProvider(
			this,
			Demonstration1ViewModelFactory(this)
		).get(Demonstration1ViewModel::class.java)
	}

	/**
	 * Sets-up the views under [binding].
	 */
	private fun setUpViews() = with(binding) {
		listSuperHeroes.initialize(
			SuperHeroAdapter(),
			LinearLayoutManager(this@Demonstration1Activity)
		)
	}

	/**
	 * Attaches the observers from [viewModel] and else-where.
	 */
	private fun attachObservers() = with(viewModel) {
		uiLiveData.observe(this@Demonstration1Activity, uiDataObserver)
	}

	/**
	 * Handles the action propagated by the [viewModel].
	 *
	 * @param model Instance of [MainModel] encapsulating the action to be performed.
	 */
	private fun onUIDataChanged(model: Demonstration1Model) = when (model.action) {

		ACTION_NONE -> Unit

		ACTION_POPULATE_SUPER_HEROES -> {
			binding.listSuperHeroes.addItems(model.superHeroes, true)
		}

		else -> Unit

	}

	/**
	 * Clears any references of [android.view.View] under [binding].
	 */
	private fun cleanUpViews() = with(binding) {
		listSuperHeroes.cleanUp()
	}

}
