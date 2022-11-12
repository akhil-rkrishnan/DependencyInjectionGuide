package app.android.diguide.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.diguide.model.ApiModel
import app.android.diguide.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainViewModel"

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _apiData = mutableStateOf<List<ApiModel.Entry?>?>(null)
    val apiData = _apiData

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
                                _apiData.value = it.entries
                            }
                        }
                    }

                    override fun onFailure(call: Call<ApiModel>, t: Throwable) {

                    }
                })

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}