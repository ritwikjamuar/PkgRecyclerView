package com.droidboi.recyclerView.utility

import android.app.Activity

import android.content.Intent

/**
 * Navigates any [Activity] to another [Activity].
 *
 * @param className [Class] of type [Activity] as the name of the [Activity] Class
 *   we want to navigate.
 */
fun Activity.navigate(className: Class<out Activity>) {
	startActivity(Intent(this, className))
}
