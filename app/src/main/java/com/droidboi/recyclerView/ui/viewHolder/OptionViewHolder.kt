package com.droidboi.recyclerView.ui.viewHolder

import com.droidboi.recyclerView.databinding.ItemOptionBinding

import com.droidboi.recyclerView.mvvm.model.MenuOption

import com.droidboi.recyclerView.utility.ViewHolderListener

import com.droidboi.recyclerView.viewHolder.BaseViewHolder

/**
 * [BaseViewHolder] to render an individual [MenuOption].
 *
 * @param binding Instance of [ItemOptionBinding] as the View-Accessor of this [BaseViewHolder].
 * @param listener Instance of [ViewHolderListener] as the event propagator of a click.
 * @author Ritwik Jamuar
 */
class OptionViewHolder(binding: ItemOptionBinding, private val listener: ViewHolderListener) :
	BaseViewHolder<ItemOptionBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() {
		viewAccessor.containerRoot.setOnClickListener {
			listener(adapterPosition)
		}
	}

	override fun cleanUp() {
		viewAccessor.containerRoot.setOnClickListener(null)
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Sets the [MenuOption] in the [ItemOptionBinding].
	 *
	 * @param option An individual [MenuOption] at the [getAdapterPosition]
	 */
	fun setOption(option: MenuOption) = with(viewAccessor) {
		valueTextOption.text = option.name
	}

}
