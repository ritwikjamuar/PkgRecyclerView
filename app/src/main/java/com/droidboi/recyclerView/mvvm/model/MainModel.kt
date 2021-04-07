package com.droidboi.recyclerView.mvvm.model

/**
 * UI Data Model for [com.droidboi.recyclerView.ui.activity.MainActivity].
 *
 * @param action [Int] denoting an Action to be performed in the UI.
 * @param menuOptions [List] of [MenuOption] as the collection of Menu Items to be rendered
 *   in the View.
 * @author Ritwik Jamuar
 */
data class MainModel(
	var action: Int,
	var menuOptions: List<MenuOption>
)
