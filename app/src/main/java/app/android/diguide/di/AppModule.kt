package app.android.diguide.di

import app.android.diguide.repository.ApiInterface
import app.android.diguide.repository.MainRepository
import app.android.diguide.repository.MainRepositoryImpl
import app.android.diguide.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideMainRepository(apiInterface: ApiInterface): MainRepository = MainRepositoryImpl(apiInterface)


}