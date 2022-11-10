package app.android.diguide.utils

import androidx.compose.runtime.Composable

@Composable
fun <T> T?.ifNotNull(block: (T) -> Unit = {},  uiBlock: @Composable (T) -> Unit = {}) {
    if (this != null) {
        block(this)
        uiBlock(this)
    }
}