package app.android.diguide.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.android.diguide.di.FirstString
import app.android.diguide.di.SecondString
import app.android.diguide.presentation.composables.CategoryComposables
import app.android.diguide.ui.theme.DependencyInjectionGuideTheme
import app.android.diguide.utils.asGroupedData
import app.android.diguide.utils.ifNotNull
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    @FirstString
    lateinit var firstString: String

    @Inject
    @SecondString
    lateinit var secondString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: firstString $firstString")
        Log.d(TAG, "onCreate: secondString $secondString")
        setContent {
            DependencyInjectionGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val screenState = viewModel.mainScreenState
                    screenState.ifNotNull { datas ->
                       CategoryComposables(map = datas.groupedItems)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DependencyInjectionGuideTheme {
        Greeting("Android")
    }
}