package com.droidboi.recyclerView.mvvm.repository

import android.content.res.AssetManager

import com.droidboi.recyclerView.mvvm.model.MenuOption

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

import java.io.IOException

import java.lang.reflect.Type

import java.util.*

/**
 * A Repository that provides all the necessary data to be shown in the UI.
 *
 * @param assetManager Instance of [AssetManager] which will be used to open files
 *   from the 'assets' folder.
 * @author Ritwik Jamuar
 */
class CommonRepository(private val assetManager: AssetManager, private val moshi: Moshi) {

	/**
	 * Provides the Menu Items to be displayed.
	 *
	 *
	 * NOTE: Add any new Menu Item here, and manage click of that
	 * in [com.droidboi.recyclerView.mvvm.viewModel.MainViewModel].
	 *
	 * @return A new [List] of [MenuOption].
	 */
	fun provideOptions(): List<MenuOption> = ArrayList<MenuOption>().apply {
		add(MenuOption(1, "RecyclerView with 1 ViewHolder and no interception of click"))
		add(MenuOption(2, "RecyclerView with 1 ViewHolder and interception on click"))
		add(MenuOption(3, "RecyclerView with 3 ViewHolder to demonstrate Pagination"))
		add(MenuOption(4, "RecyclerView with 4 ViewHolder to demonstrate News Feed like UI"))
	}

	/*-------------------------------------- Private Methods -------------------------------------*/

	/**
	 * Parses a given JSON into the intended class.
	 *
	 * @param T Any Data Class.
	 * @param json [String] denoting the JSON Content we want to parse.
	 * @param type [Type] of [T].
	 * @return An instance of [T] if the [json] is not null, else null if the [json] is null
	 * or there was some exception while parsing the [json].
	 */
	private fun <T> parseJSON(json: String?, type: Type): T? = try {

		// Check whether the 'json' is null or not.
		if (json == null) {

			// At this point, the 'json' is null, so we can't do any parsing with it.
			// So, return null.
			null

		} else {
			moshi.adapter<T>(type).fromJson(json) // Try to parse the 'json' using 'moshi'.
		}

	} catch (e: IOException) {

		// At this point, there was some problem in parsing the JSON, so we print the Stack Trace
		// and return null.
		e.printStackTrace()
		null

	}

	/**
	 * Loads a JSON File from Assets using [assetManager].
	 *
	 * @param fileName [String] denoting the JSON File Name present in the 'assets' folder.
	 * @return [String] as the JSON if the [fileName] is correct or there was no problem in
	 * opening the File with [fileName], else null.
	 */
	private fun loadJSONFromAssets(fileName: String): String? = try {

		// Check whether the file name is correct or not.
		if (fileName.isEmpty() || !fileName.endsWith(".json")) {

			// At this point, the fileName supplied is not valid, so we are returning 'null'
			null

		} else {
			val stream = assetManager.open(fileName) // Open the File using 'assetManager'.
			val size = stream.available() // Determine the size of the file.
			val buffer = ByteArray(size) // Create an array of Bytes.
			stream.read(buffer) // Write the file into the 'buffer'.
			stream.close() // Close the file.
			String(buffer, Charsets.UTF_8) // Create a String using this 'buffer'.
		}
	} catch (e: IOException) {

		// At this point something went wrong while doing the I/O Operation with the file.
		// So we simply return null.
		e.printStackTrace()
		null

	}

}
