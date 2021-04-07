package com.droidboi.recyclerView.mvvm.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.ACTION_NONE
import com.droidboi.recyclerView.mvvm.uiData.Demonstration1Model

import com.droidboi.recyclerView.mvvm.viewModel.Demonstration1ViewModel

import com.droidboi.recyclerView.ui.activity.Demonstration1Activity

import com.squareup.moshi.Moshi

import java.util.LinkedList

/**
 * [ViewModelProvider] of [Demonstration1ViewModel].
 *
 * @author Ritwik Jamuar
 */
class Demonstration1ViewModelFactory(private val activity: Demonstration1Activity) :
	ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		Demonstration1ViewModel(
			Demonstration1Model(ACTION_NONE, LinkedList()),
			CommonRepository(activity.assets, Moshi.Builder().build()),
		) as T

}
