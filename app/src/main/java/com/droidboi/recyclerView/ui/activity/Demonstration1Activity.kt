package com.droidboi.recyclerView.ui.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import com.droidboi.recyclerView.R

/**
 * [AppCompatActivity] to demonstrate the [androidx.recyclerview.widget.RecyclerView]
 * with the use-case below:
 *
 * Render a Collection of Superheroes with no click listener on each hero.
 */
class Demonstration1Activity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_demonstration1)
	}

}
