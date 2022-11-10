package app.android.diguide.repository

import app.android.diguide.model.ApiModel
import retrofit2.Call
import retrofit2.http.GET

interface MainRepository {
    @GET("entries")
    suspend fun getData(): Call<ApiModel>
}