package com.droidboi.recyclerView.ui.activity

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.contrib.RecyclerViewActions

import androidx.test.espresso.intent.Intents.intended

import androidx.test.espresso.intent.matcher.IntentMatchers

import androidx.test.espresso.intent.rule.IntentsTestRule

import androidx.test.espresso.matcher.ViewMatchers

import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.ext.junit.runners.AndroidJUnit4

import com.droidboi.recyclerView.R

import com.droidboi.recyclerView.ui.viewHolder.OptionViewHolder

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

/**
 * Instrumentation Test Class of [MainActivity].
 *
 * @author Ritwik Jamuar
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * [ActivityScenarioRule] to test out an [android.app.Activity].
	 */
	@get:Rule
	var activityScenarioRule : ActivityScenarioRule<MainActivity> =
		ActivityScenarioRule(MainActivity::class.java)

	/**
	 * [IntentsTestRule] to test out an [android.content.Intent]
	 */
	@Suppress("DEPRECATION")
	@get:Rule
	val intentsTestRule : IntentsTestRule<MainActivity> =
		IntentsTestRule(MainActivity::class.java)

	/*-------------------------------------- JUnit Callbacks -------------------------------------*/

	@Before
	fun setUp() {
	}

	@After
	fun tearDown() {
		activityScenarioRule.scenario.close()
	}

	/*---------------------------------------- Test Cases ----------------------------------------*/

	/**
	 * Test Case #1:
	 *
	 * Description: Click on the first Menu Option, and check what happens.
	 *
	 * Test Data: Menu Options populated from the [androidx.lifecycle.ViewModel].
	 *
	 * Expected Result: User is navigated to [Demonstration1Activity].
	 */
	@Test
	fun selectFirstMenuOption() {

		// Perform click on the first position in the RecyclerView.
		onView(ViewMatchers.withId(R.id.list_options)).perform(
			RecyclerViewActions.actionOnItemAtPosition<OptionViewHolder>(
				0,
				click()
			)
		)

		// Check if the User is navigated to Demonstration1Activity or not.
		intended(IntentMatchers.hasComponent(Demonstration1Activity::class.java.name))

	}

	/**
	 * Test Case #2:
	 *
	 * Description: Click on the second Menu Option, and check what happens.
	 *
	 * Test Data: Menu Options populated from the [androidx.lifecycle.ViewModel].
	 *
	 * Expected Result: Nothing happens since Demonstration2Activity is not yet implemented.
	 */
	@Test
	fun selectSecondMenuOption() {

		// Perform click on the second position in the RecyclerView.
		onView(ViewMatchers.withId(R.id.list_options)).perform(
			RecyclerViewActions.actionOnItemAtPosition<OptionViewHolder>(
				1,
				click()
			)
		)

		// TODO : Uncomment below line when the Demonstration2Activity is available.
		// intended(IntentMatchers.hasComponent(Demonstration2Activity::class.java.name))

	}

	/**
	 * Test Case #3:
	 *
	 * Description: Click on the third Menu Option, and check what happens.
	 *
	 * Test Data: Menu Options populated from the [androidx.lifecycle.ViewModel].
	 *
	 * Expected Result: Nothing happens since Demonstration3Activity is not yet implemented.
	 */
	@Test
	fun selectThirdMenuOption() {

		// Perform click on the third position in the RecyclerView.
		onView(ViewMatchers.withId(R.id.list_options)).perform(
			RecyclerViewActions.actionOnItemAtPosition<OptionViewHolder>(
				2,
				click()
			)
		)

		// TODO : Uncomment below line when the Demonstration3Activity is available.
		// intended(IntentMatchers.hasComponent(Demonstration3Activity::class.java.name))

	}

	/**
	 * Test Case #4:
	 *
	 * Description: Click on the fourth Menu Option, and check what happens.
	 *
	 * Test Data: Menu Options populated from the [androidx.lifecycle.ViewModel].
	 *
	 * Expected Result: Nothing happens since Demonstration4Activity is not yet implemented.
	 */
	@Test
	fun selectFourthMenuOption() {

		// Perform click on the fourth position in the RecyclerView.
		onView(ViewMatchers.withId(R.id.list_options)).perform(
			RecyclerViewActions.actionOnItemAtPosition<OptionViewHolder>(
				3,
				click()
			)
		)

		// TODO : Uncomment below line when the Demonstration4Activity is available.
		// intended(IntentMatchers.hasComponent(Demonstration4Activity::class.java.name))

	}

}
