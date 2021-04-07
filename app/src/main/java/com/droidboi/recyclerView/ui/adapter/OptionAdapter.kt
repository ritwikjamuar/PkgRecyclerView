package com.droidboi.recyclerView.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import com.droidboi.recyclerView.adapter.BaseSingleVHAdapter

import com.droidboi.recyclerView.databinding.ItemOptionBinding

import com.droidboi.recyclerView.mvvm.model.MenuOption

import com.droidboi.recyclerView.ui.viewHolder.OptionViewHolder

import com.droidboi.recyclerView.utility.AdapterListener

/**
 * [BaseSingleVHAdapter] to render [MenuOption]s in [OptionViewHolder].
 *
 * @param listener Instance of [AdapterListener] as the event propagator.
 * @author Ritwik Jamuar
 */
class OptionAdapter(private val listener: AdapterListener<MenuOption>) :
	BaseSingleVHAdapter<MenuOption, OptionViewHolder>() {

	/*------------------------------ BaseSingleVHAdapter Callbacks -------------------------------*/

	override fun provideViewHolder(parent: ViewGroup): OptionViewHolder = OptionViewHolder(
		ItemOptionBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
	) { position ->
		listener.invoke(list[position])
	}

	override fun onBind(viewHolder: OptionViewHolder, position: Int) = with(viewHolder) {
		setOption(list[position])
	}

}
