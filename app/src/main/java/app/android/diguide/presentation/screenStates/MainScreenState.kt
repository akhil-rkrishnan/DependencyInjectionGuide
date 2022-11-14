package app.android.diguide.presentation.screenStates

import app.android.diguide.model.ApiModel

data class MainScreenState(
    val isLoading: Boolean = false,
    val items: List<ApiModel.Entry?>? = null,
    val groupedItems: Map<String, List<ApiModel.Entry?>>? = null,
    val isError: Boolean = false,
    val error: String? = null,
    val optionalMessage: String? = null
)