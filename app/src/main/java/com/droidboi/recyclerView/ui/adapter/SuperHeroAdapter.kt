package com.droidboi.recyclerView.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import com.droidboi.recyclerView.adapter.BaseSingleVHAdapter

import com.droidboi.recyclerView.databinding.ItemSuperHeroBinding

import com.droidboi.recyclerView.mvvm.model.SuperHero

import com.droidboi.recyclerView.ui.viewHolder.SuperHeroViewHolder

/**
 * [BaseSingleVHAdapter] to render [SuperHero]s in [SuperHeroViewHolder].
 *
 * @author Ritwik Jamuar
 */
class SuperHeroAdapter : BaseSingleVHAdapter<SuperHero, SuperHeroViewHolder>() {

	/*------------------------------ BaseSingleVHAdapter Callbacks -------------------------------*/

	override fun provideViewHolder(parent: ViewGroup): SuperHeroViewHolder = SuperHeroViewHolder(
		ItemSuperHeroBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
	)

	override fun onBind(viewHolder: SuperHeroViewHolder, position: Int) = with(viewHolder) {
		setSuperHero(list[position])
	}

}
