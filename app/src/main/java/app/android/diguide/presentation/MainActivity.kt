package app.android.diguide.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.android.diguide.ui.theme.DependencyInjectionGuideTheme
import app.android.diguide.utils.ifNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyInjectionGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val items = viewModel.apiData.value
                    items.ifNotNull { datas ->
                        LazyColumn {
                            datas.forEachIndexed { index, item ->
                                item {
                                    Text(
                                        text = "Entry: ${item?.category}",
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            start = if (index % 3 == 0) 25.dp else if (index % 4 == 0) 20.dp else 10.dp
                                        ),
                                        style = TextStyle(
                                            color = if (index % 3 == 0) Color.Black else Color.Red,
                                            fontSize = if (index % 4 == 0) 14.sp else 18.sp
                                        )
                                    )
                                }
                            }
                        }
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