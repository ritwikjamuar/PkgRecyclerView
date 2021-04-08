package com.droidboi.recyclerView.mvvm.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.lifecycle.Observer

import com.droidboi.recyclerView.mvvm.model.MenuOption

import com.droidboi.recyclerView.mvvm.repository.CommonRepository

import com.droidboi.recyclerView.mvvm.uiData.*

import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Rule

import org.junit.rules.TestRule

import org.junit.runner.RunWith

import org.mockito.Mock

import org.mockito.Mockito.*

import org.mockito.junit.MockitoJUnitRunner

import java.util.*

/**
 * Unit Test Class of [MainViewModel].
 *
 * @author Ritwik Jamuar
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

	/*---------------------------------------- Components ----------------------------------------*/

	/**
	 * [TestRule] annotated with [Rule] to provide the [InstantTaskExecutorRule] to the JUnit.
	 */
	@get:Rule
	val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

	/**
	 * Mock [CommonRepository] fulfilled by [org.mockito.Mockito].
	 */
	@Mock
	private lateinit var repository: CommonRepository

	/**
	 * Reference of [MainViewModel] on which test cases will be validated upon.
	 */
	private val viewModel: MainViewModel by lazy {
		MainViewModel(MainModel(ACTION_NONE, LinkedList()), repository)
	}

	/**
	 * Mock [Observer] fulfilled by [org.mockito.Mockito].
	 */
	@Mock
	private lateinit var observer: Observer<MainModel>

	/*-------------------------------------- JUnit Callbacks -------------------------------------*/

	@Before
	fun setUp() {
		viewModel.uiLiveData.observeForever(observer)
	}

	@After
	fun tearDown() {
		viewModel.uiLiveData.removeObserver(observer)
	}

	/*---------------------------------------- Test Cases ----------------------------------------*/

	/**
	 * Test Case #1:
	 *
	 * Description: Check the method [MainViewModel.onUIStarted] and observe the change in UI.
	 *
	 * Test Data: [List] of [MenuOption].
	 *
	 * Expected Result: The value of [MainModel.action] should be [ACTION_POPULATE_MENU_OPTIONS].
	 */
	@Test
	fun checkOnUIStarted() {

		// Populate the Menu Options by calling the 'onUIStarted' in the 'viewModel',
		viewModel.onUIStarted()

		// Verify that the 'observer' has received updates on 'MainModel' in the UI 1 Time.
		verify(observer, times(1))
			.onChanged(viewModel.uiLiveData.value)

		// Assert that the 'action'from latest observed 'MainModel' is to Populate the Menu Options
		// in the UI.
		assertThat(
			ACTION_POPULATE_MENU_OPTIONS == viewModel.uiLiveData.value?.action
		)

	}

	/**
	 * Test Case #2:
	 *
	 * Description: Check the method [MainViewModel.onOptionSelected] and observe the changes in UI
	 * when the First [MenuOption] from the UI is selected.
	 *
	 * Test Data: A [MenuOption] provided from [provideMockMenuOption].
	 *
	 * Expected Result: The value of [MainModel.action] should be [ACTION_NAVIGATE_TO_DEMO_1].
	 */
	@Test
	fun checkOnFirstOptionSelectedFromRecyclerView() = testMenuOptionSelectionFromUI(
		provideMockMenuOption(1),
		ACTION_NAVIGATE_TO_DEMO_1
	)

	/**
	 * Test Case #3:
	 *
	 * Description: Check the method [MainViewModel.onOptionSelected] and observe the changes in UI
	 * when the Second [MenuOption] from the UI is selected.
	 *
	 * Test Data: A [MenuOption] provided from [provideMockMenuOption].
	 *
	 * Expected Result: The value of [MainModel.action] should be [ACTION_NAVIGATE_TO_DEMO_2].
	 */
	@Test
	fun checkOnSecondOptionSelectedFromRecyclerView() = testMenuOptionSelectionFromUI(
		provideMockMenuOption(2),
		ACTION_NAVIGATE_TO_DEMO_2
	)

	/**
	 * Test Case #4:
	 *
	 * Description: Check the method [MainViewModel.onOptionSelected] and observe the changes in UI
	 * when the Third [MenuOption] from the UI is selected.
	 *
	 * Test Data: A [MenuOption] provided from [provideMockMenuOption].
	 *
	 * Expected Result: The value of [MainModel.action] should be [ACTION_NAVIGATE_TO_DEMO_3].
	 */
	@Test
	fun checkOnThirdOptionSelectedFromRecyclerView() = testMenuOptionSelectionFromUI(
		provideMockMenuOption(3),
		ACTION_NAVIGATE_TO_DEMO_3
	)

	/**
	 * Test Case #5:
	 *
	 * Description: Check the method [MainViewModel.onOptionSelected] and observe the changes in UI
	 * when the Fourth [MenuOption] from the UI is selected.
	 *
	 * Test Data: A [MenuOption] provided from [provideMockMenuOption].
	 *
	 * Expected Result: The value of [MainModel.action] should be [ACTION_NAVIGATE_TO_DEMO_4].
	 */
	@Test
	fun checkOnFourthOptionSelectedFromRecyclerView() = testMenuOptionSelectionFromUI(
		provideMockMenuOption(4),
		ACTION_NAVIGATE_TO_DEMO_4
	)

	/**
	 * Test Case #6:
	 *
	 * Description: Check the method [MainViewModel.onOptionSelected] observe the changes in UI
	 * when any arbitrary [MenuOption] is provided.
	 *
	 * Test Data: A [MenuOption] provided from [provideMockMenuOption].
	 *
	 * Expected Result: No Action is reported in the UI.
	 */
	@Test
	fun checkOnOptionProvidedArbitrarily() {

		// Populate the Menu Options by calling the 'onUIStarted' in the 'viewModel',
		viewModel.onUIStarted()

		// Pass the 'option' from the method 'provideMockMenuOption' in the Method 'onOptionSelected'
		// of the 'viewModel'.
		viewModel.onOptionSelected(provideMockMenuOption(1000))

		// Verify that the 'observer' has received updates on 'MainModel' in the UI 1 Time.
		verify(observer, times(1))
			.onChanged(viewModel.uiLiveData.value)

	}

	/*-------------------------------------- Private Methods -------------------------------------*/

	/**
	 * Creates a Mock [MenuOption] based on the [position], which is synonymous to the position
	 * of the Option in the UI.
	 *
	 * @param position [Int] denoting the Position in the UI.
	 * @return New Instance of [MenuOption].
	 */
	private fun provideMockMenuOption(position: Int) = MenuOption(
		position,
		when(position) {
			1 -> "RecyclerView with 1 ViewHolder and no interception of click"
			2 -> "RecyclerView with 1 ViewHolder and interception on click"
			3 -> "RecyclerView with 3 ViewHolder to demonstrate Pagination"
			4 -> "RecyclerView with 4 ViewHolder to demonstrate News Feed like UI"
			else -> "Some Dummy Option"
		}
	)

	/**
	 * Tests out the method [MainViewModel.onOptionSelected] on the different [option] provided.
	 *
	 * @param option Instance of [MenuOption] denoting the User Choice made in the UI.
	 * @param action [Int] denoting the Action on UI that is expected.
	 */
	private fun testMenuOptionSelectionFromUI(option: MenuOption, action: Int) {

		// Populate the Menu Options by calling the 'onUIStarted' in the 'viewModel',
		viewModel.onUIStarted()

		// Pass the 'option' in the Method 'onOptionSelected' of the 'viewModel'.
		viewModel.onOptionSelected(option)

		// Verify that the 'observer' has received updates on 'MainModel' in the UI 2 Times.
		verify(observer, times(2))
			.onChanged(viewModel.uiLiveData.value)

		// Assert the desired 'action' against the latest observed 'MainModel'.
		assertThat(
			action == viewModel.uiLiveData.value?.action
		)

	}

}
