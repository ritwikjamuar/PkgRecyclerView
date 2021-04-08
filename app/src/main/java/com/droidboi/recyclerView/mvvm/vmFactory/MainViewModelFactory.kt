package com.droidboi.recyclerView.mvvm.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.ACTION_NONE
import com.droidboi.recyclerView.mvvm.uiData.MainModel

import com.droidboi.recyclerView.mvvm.viewModel.MainViewModel

import com.droidboi.recyclerView.ui.activity.MainActivity

import com.squareup.moshi.Moshi

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import java.util.LinkedList

/**
 * [ViewModelProvider] of [MainViewModel].
 *
 * @author Ritwik Jamuar
 */
class MainViewModelFactory(private val activity: MainActivity) :
	ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		MainViewModel(
			MainModel(ACTION_NONE, LinkedList()),
			CommonRepository(
				activity.assets,
				Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			),
		) as T

}
