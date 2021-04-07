package com.droidboi.recyclerView.mvvm.repository

import com.droidboi.recyclerView.mvvm.model.MenuOption

/**
 * A Repository that provides all the necessary data to be shown in the UI.
 *
 * @author Ritwik Jamuar
 */
class CommonRepository {

	/**
	 * Provides the Menu Items to be displayed.
	 *
	 *
	 * NOTE: Add any new Menu Item here, and manage click of that
	 * in [com.droidboi.recyclerView.mvvm.viewModel.MainViewModel].
	 *
	 * @return A new [List] of [MenuOption].
	 */
	fun provideOptions(): List<MenuOption> = ArrayList<MenuOption>().apply {
		add(MenuOption(1, "RecyclerView with 1 ViewHolder and no interception of click"))
		add(MenuOption(2, "RecyclerView with 1 ViewHolder and interception on click"))
		add(MenuOption(3, "RecyclerView with 3 ViewHolder to demonstrate Pagination"))
		add(MenuOption(4, "RecyclerView with 4 ViewHolder to demonstrate News Feed like UI"))
	}

}
