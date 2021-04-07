package com.droidboi.recyclerView.mvvm.repository

import android.content.res.AssetManager

import com.droidboi.recyclerView.mvvm.model.MenuOption

import java.io.IOException

/**
 * A Repository that provides all the necessary data to be shown in the UI.
 *
 * @param assetManager Instance of [AssetManager] which will be used to open files
 *   from the 'assets' folder.
 * @author Ritwik Jamuar
 */
class CommonRepository(private val assetManager: AssetManager) {

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
