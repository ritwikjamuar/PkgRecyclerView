package com.droidboi.recyclerView.mvvm.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.droidboi.recyclerView.mvvm.model.ACTION_NONE
import com.droidboi.recyclerView.mvvm.model.MainModel
import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.viewModel.MainViewModel
import java.util.*

/**
 * [ViewModelProvider] of [MainViewModel].
 *
 * @author Ritwik Jamuar
 */
class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		MainViewModel(MainModel(ACTION_NONE, LinkedList()), CommonRepository()) as T

}
