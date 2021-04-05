package com.droidboi.recyclerView.viewHolder

import android.view.View

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract [RecyclerView.ViewHolder] for handling common set-up required for
 * any General Purpose Lifecycle Aware [RecyclerView.ViewHolder].
 *
 * @param ViewAccessor Any Class as an Accessor of the [View]s in this [RecyclerView.ViewHolder].
 * @param viewAccessor Instance of [ViewAccessor].
 * @param view Instance of [View] on which this [RecyclerView.ViewHolder] is inflated on.
 * @author Ritwik Jamuar
 */
abstract class BaseViewHolder<ViewAccessor>(
    protected val viewAccessor: ViewAccessor,
    view: View
) : RecyclerView.ViewHolder(view), LifecycleOwner {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * Reference of [LifecycleRegistry] to control the Lifecycle of this [BaseViewHolder].
	 */
	private val lifecycleRegistry: LifecycleRegistry by lazy {
		LifecycleRegistry(this)
	}

	/*------------------------------------ Initializer Block -------------------------------------*/

	init {
		initialize()
	}

	/*----------------------------------- Lifecycle Callbacks ------------------------------------*/

	override fun getLifecycle(): Lifecycle = lifecycleRegistry

	/*-------------------------------------- Public Methods --------------------------------------*/

	/**
	 * Notifies that this [BaseViewHolder] is attached to the Window.
	 */
	fun markAttach() {
		lifecycleRegistry.currentState = Lifecycle.State.STARTED
		initializeComponents()
	}

	/**
	 * Notifies that this [BaseViewHolder] is detached from the Window.
	 */
	fun markDetach() {
		lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
		cleanUp()
	}

	/*-------------------------------------- Private Methods -------------------------------------*/

	/**
	 * Initializes components of this [BaseViewHolder].
	 */
	private fun initialize() {
		lifecycleRegistry.currentState = Lifecycle.State.CREATED
	}

	/*------------------------------------- Abstract Methods -------------------------------------*/

	/**
	 * Tells this [BaseViewHolder] to initialize it's components.
	 */
	protected abstract fun initializeComponents()

	/**
	 * Tells this [BaseViewHolder] to perform Clean-Up procedures for avoiding Memory Leak.
	 */
	protected abstract fun cleanUp()

}
