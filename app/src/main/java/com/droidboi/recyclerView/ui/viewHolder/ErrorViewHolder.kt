package com.droidboi.recyclerView.ui.viewHolder

import com.droidboi.recyclerView.databinding.ItemErrorBinding

import com.droidboi.recyclerView.viewHolder.BaseViewHolder

/**
 * [BaseViewHolder] to render a Error Item.
 *
 * @param binding Instance of [ItemErrorBinding] as the View-Accessor of this [BaseViewHolder].
 * @param listener Lambda Variable as the Listener of Retry Button.
 * @author Ritwik Jamuar
 */
class ErrorViewHolder(binding: ItemErrorBinding, private val listener: () -> Unit) :
	BaseViewHolder<ItemErrorBinding>(binding, binding.root) {

	/*--------------------------------- BaseViewHolder Callbacks ---------------------------------*/

	override fun initializeComponents() = with(viewAccessor) {
		buttonRetry.setOnClickListener {
			listener.invoke()
		}
	}

	override fun cleanUp() = with(viewAccessor) {
		buttonRetry.setOnClickListener(null)
	}

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Sets the description of the Error.
	 *
	 * @param description [String] denoting the Error Description.
	 */
	fun setDescription(description: String) = with(viewAccessor) {
		valueTextErrorDescription.text = description
	}

}
