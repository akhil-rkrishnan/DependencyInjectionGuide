package app.android.diguide.presentation

import app.android.diguide.di.appModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProvider
import org.koin.test.mock.MockProviderRule
import kotlin.test.assertEquals

class MainViewModelTest : KoinTest {
    val viewModel by inject<MainViewModel>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        MockProvider.makeMock(it)
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `unit test`() {
        assertEquals(viewModel.getTestString(), "hello")
    }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}

@ExperimentalCoroutinesApi
class MainDispatcherRule(val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {

    override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description?) = Dispatchers.resetMain()

}