package app.android.diguide.repository

import app.android.diguide.model.ApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("entries")
    fun getData(): Call<ApiModel>
}