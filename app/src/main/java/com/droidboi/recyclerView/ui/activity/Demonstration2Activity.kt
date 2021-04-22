package com.droidboi.recyclerView.ui.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.view.LayoutInflater

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.droidboi.recyclerView.BuildConfig

import com.droidboi.recyclerView.databinding.ActivityDemonstration2Binding

import com.droidboi.recyclerView.mvvm.uiData.*

import com.droidboi.recyclerView.mvvm.viewModel.Demonstration2ViewModel

import com.droidboi.recyclerView.mvvm.vmFactory.Demonstration2ViewModelFactory

import com.droidboi.recyclerView.ui.adapter.CarBrandAdapter

import com.droidboi.recyclerView.utility.addItems
import com.droidboi.recyclerView.utility.cleanUp
import com.droidboi.recyclerView.utility.initialize

import com.squareup.picasso.Picasso

/**
 * [AppCompatActivity] to demonstrate the [androidx.recyclerview.widget.RecyclerView]
 * with the use-case below:
 *
 * Render a Collection of Car Brands with no click listener on each Car Brand.
 * The individual Car Brand contains [androidx.recyclerview.widget.RecyclerView]s
 * to render the Founders and Popular Cars as well.
 * The Car Brand will be populated in the pagination fashion.
 * @author Ritwik Jamuar
 */
class Demonstration2Activity : AppCompatActivity() {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * Reference of [ActivityDemonstration2Binding] to access the Views
	 * of this [android.app.Activity].
	 */
	private lateinit var binding: ActivityDemonstration2Binding

	/**
	 * Reference of [Demonstration2ViewModel] to notify actions and observe the changes.
	 */
	private lateinit var viewModel: Demonstration2ViewModel

	/**
	 * Reference of [Picasso] to facilitate the the Image Loading over the Network.
	 */
	private val picasso: Picasso by lazy {
		Picasso.Builder(this)
			.loggingEnabled(true)
			.indicatorsEnabled(BuildConfig.DEBUG)
			.listener { _, _, exception ->
				android.util.Log.e(Demonstration2Activity::class.java.name, "$exception")
			}
			.build()
	}

	/*---------------------------------------- Observers -----------------------------------------*/

	/**
	 * [Observer] of [Demonstration2ViewModel.uiLiveData].
	 */
	private val uiDataObserver: Observer<Demonstration2Model> = Observer { model ->
		onUIDataChanged(model)
	}

	/*-------------------------------------- View Listeners --------------------------------------*/

	/**
	 * [RecyclerView.OnScrollListener] to intercept scroll on the Car Brands.
	 */
	private val scrollListener = object : RecyclerView.OnScrollListener() {
		override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
			super.onScrolled(recyclerView, dx, dy)
			with(binding.listCarBrands) {
				(layoutManager as? LinearLayoutManager)?.let { layoutManager ->
					viewModel.onScrolled(
						layoutManager.childCount,
						layoutManager.itemCount,
						layoutManager.findFirstVisibleItemPosition(),
						(adapter as? CarBrandAdapter)?.isLoading() ?: false,
						(adapter as? CarBrandAdapter)?.isError() ?: false
					)
				}
			}
		}
	}

	/**
	 * Click Listener of Retry in the Error View.
	 */
	private val errorListener: () -> Unit = {
		viewModel.onRetryClicked()
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
		binding = ActivityDemonstration2Binding.inflate(LayoutInflater.from(this))
		viewModel = ViewModelProvider(
			this,
			Demonstration2ViewModelFactory(this)
		).get(Demonstration2ViewModel::class.java)
	}

	/**
	 * Sets-up the views under [binding].
	 */
	private fun setUpViews() = with(binding) {
		setSupportActionBar(toolbarDemonstration2)
		supportActionBar?.let { actionBar ->
			actionBar.setDisplayHomeAsUpEnabled(true)
			actionBar.setDisplayShowHomeEnabled(true)
		}
		toolbarDemonstration2.setNavigationOnClickListener {
			onBackPressed()
		}
		listCarBrands.initialize(
			CarBrandAdapter(picasso, errorListener),
			LinearLayoutManager(this@Demonstration2Activity),
			scrollListener
		)
	}

	/**
	 * Attaches the observers from [viewModel] and else-where.
	 */
	private fun attachObservers() = with(viewModel) {
		uiLiveData.observe(this@Demonstration2Activity, uiDataObserver)
	}

	/**
	 * Handles the action propagated by the [viewModel].
	 *
	 * @param model Instance of [MainModel] encapsulating the action to be performed.
	 */
	private fun onUIDataChanged(model: Demonstration2Model) = when (model.action) {

		ACTION_NONE -> Unit

		ACTION_POPULATE_CAR_BRANDS -> model.recentlyPopulatedBrands?.let { brands ->
			binding.listCarBrands.addItems(brands, false)
		}

		ACTION_SHOW_LOADING_ON_LIST -> with(binding.listCarBrands) {
			(adapter as? CarBrandAdapter)?.let { adapter ->
				post {
					adapter.addLoading()
				}
			}
		} ?: Unit

		ACTION_HIDE_LOADING_ON_LIST -> with(binding.listCarBrands) {
			(adapter as? CarBrandAdapter)?.let { adapter ->
				post {
					adapter.removeLoading()
				}
			}
		}

		ACTION_SHOW_ERROR_ON_LIST -> with(binding.listCarBrands) {
			(adapter as? CarBrandAdapter)?.let { adapter ->
				post {
					adapter.addError(model.errorDescription)
				}
			}
		}

		ACTION_HIDE_ERROR_ON_LIST -> with(binding.listCarBrands) {
			(adapter as? CarBrandAdapter)?.let { adapter ->
				post {
					adapter.removeError()
				}
			}
		}

		else -> Unit

	}

	/**
	 * Clears any references of [android.view.View] under [binding].
	 */
	private fun cleanUpViews() = with(binding) {
		listCarBrands.cleanUp()
	}

}
