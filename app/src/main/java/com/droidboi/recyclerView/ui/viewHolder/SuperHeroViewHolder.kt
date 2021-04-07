package com.droidboi.recyclerView.ui.viewHolder

import com.droidboi.recyclerView.databinding.ItemSuperHeroBinding

import com.droidboi.recyclerView.mvvm.model.SuperHero

import com.droidboi.recyclerView.viewHolder.BaseViewHolder

/**
 * [BaseViewHolder] to render an individual [SuperHero].
 *
 * @param binding Instance of [ItemSuperHeroBinding] as the View-Accessor of this [BaseViewHolder].
 * @author Ritwik Jamuar
 */
class SuperHeroViewHolder(binding: ItemSuperHeroBinding) :
	BaseViewHolder<ItemSuperHeroBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = Unit

	override fun cleanUp() = Unit

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Sets the [SuperHero] in the [ItemSuperHeroBinding].
	 *
	 * @param hero An individual [SuperHero] at the [getAdapterPosition].
	 */
	fun setSuperHero(hero: SuperHero) = with(viewAccessor) {
		valueTextSuperHeroName.text = hero.name
		valueTextSuperHeroPowers.text = hero.getPowers()
	}

}
