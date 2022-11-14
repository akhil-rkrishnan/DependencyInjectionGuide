package app.android.diguide.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.android.diguide.presentation.composables.CategoryComposables
import app.android.diguide.ui.theme.DependencyInjectionGuideTheme
import app.android.diguide.utils.ifNotNull
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {
    // initialise viewmodel

    private val viewModel by viewModel<MainViewModel>()
    //OR
    // private val viewModel by inject<MainViewModel>()

    // inject the scope.
    private val scope by activityScope()
    private val scopeH by inject<String>(named("firstString"))
    private val scopeW by inject<String>(named("secondString"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyInjectionGuideTheme {
                // we can initialize viewmodel as below also
                // val vm = getViewModel<MainViewModel>()
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