package com.droidboi.recyclerView.mvvm.uiData

import com.droidboi.recyclerView.mvvm.model.SuperHero

/**
 * UI Data Model for [com.droidboi.recyclerView.ui.activity.Demonstration1Activity].
 *
 * @param action [Int] denoting an Action to be performed in the UI.
 * @param superHeroes [List] of [SuperHero] as the collection of Super Heroes to be rendered
 *   in the View.
 * @author Ritwik Jamuar
 */
data class Demonstration1Model(
	var action: Int,
	var superHeroes: List<SuperHero>
)
