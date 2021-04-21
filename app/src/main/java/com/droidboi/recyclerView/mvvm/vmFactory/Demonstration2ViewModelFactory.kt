package com.droidboi.recyclerView.mvvm.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.ACTION_NONE

import com.droidboi.recyclerView.mvvm.uiData.Demonstration2Model

import com.droidboi.recyclerView.mvvm.viewModel.Demonstration2ViewModel

import com.droidboi.recyclerView.ui.activity.Demonstration2Activity

import com.squareup.moshi.Moshi

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import java.util.*

/**
 * [ViewModelProvider] of [Demonstration2ViewModel].
 *
 * @param activity Instance of [Demonstration2Activity] to provide the Resource Assets.
 * @author Ritwik Jamuar
 */
class Demonstration2ViewModelFactory(private val activity: Demonstration2Activity) :
	ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		Demonstration2ViewModel(
			Demonstration2Model(ACTION_NONE, LinkedList()),
			CommonRepository(
				activity.assets,
				Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			),
		) as T

}
