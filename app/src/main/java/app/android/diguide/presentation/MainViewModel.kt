package app.android.diguide.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.diguide.model.ApiModel
import app.android.diguide.presentation.screenStates.MainScreenState
import app.android.diguide.repository.MainRepository
import app.android.diguide.utils.asGroupedData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainViewModel"

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _mainScreenState by mutableStateOf(MainScreenState(isLoading = true))
    val mainScreenState get() = _mainScreenState

    init {
        doANetworkCall()
    }

    private fun doANetworkCall() {
        viewModelScope.launch {
            try {
                mainRepository.getData().enqueue(object : Callback<ApiModel> {
                    override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                        Log.e(TAG, "onResponse: ${response.body()}")
                        if (response.isSuccessful) {
                            response.body()?.let {
                                _mainScreenState = _mainScreenState.copy(
                                    isLoading = false,
                                    items = it.entries,
                                    groupedItems = it.entries?.sortedBy { it?.category }
                                        .asGroupedData()?.toSortedMap()
                                )
                            }
                        }
                    }

                    override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                        _mainScreenState = _mainScreenState.copy(
                            isLoading = false,
                            isError = true,
                            error = t.message
                        )
                    }
                })

            } catch (ex: Exception) {
                ex.printStackTrace()
                _mainScreenState = _mainScreenState.copy(
                    isLoading = false,
                    isError = true,
                    error = ex.message
                )
            }
        }
    }

}

