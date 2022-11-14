package app.android.diguide.presentation.composables

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.android.diguide.R
import app.android.diguide.model.ApiModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CategoryComposables(map: Map<String, List<ApiModel.Entry?>>?) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        map?.forEach {
            var isExpanded by remember {
                mutableStateOf(false)
            }
            TitleComposable(it.key, isExpanded, onClick = {
                isExpanded = !isExpanded
            })

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.animateEnterExit(enter = slideInVertically())) {
                    it.value.forEachIndexed { index, entry ->
                        BodyComposable(item = entry)
                    }
                }

            }

            /*if (isExpanded) {
                it.value.forEachIndexed { index, entry ->
                    BodyComposable(item = entry)
                }
            }*/
        }
    }

}

@Composable
fun TitleComposable(title: String, isExpanded: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .animateContentSize()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = title, style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight(500),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontStyle = FontStyle.Normal
            )
        )
        Image(
            painter = if (isExpanded) painterResource(id = R.drawable.up_arrow) else painterResource(
                id = R.drawable.down_arrow
            ),
            contentDescription = "Down arrow!",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BodyComposable(item: ApiModel.Entry?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp)
    ) {
        Text(
            text = item?.description ?: "No description", style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.roboto_slab_regular)),
                fontSize = 13.sp,
                fontStyle = FontStyle.Normal
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.3.dp)
                .background(Color.DarkGray)
        )
        Text(
            text = item?.link ?: "No Link", style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.roboto_slab_light)),
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic
            )
        )
    }

}