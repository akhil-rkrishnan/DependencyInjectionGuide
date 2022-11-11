package app.android.diguide.di

import app.android.diguide.repository.ApiInterface
import app.android.diguide.repository.MainRepository
import app.android.diguide.repository.MainRepositoryImpl
import app.android.diguide.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideMainRepository(apiInterface: ApiInterface): MainRepository =
        MainRepositoryImpl(apiInterface)

    @FirstString
    @Provides
    @Singleton
    fun providesFirstString(): String = "This is the first string"

    @SecondString
    @Provides
    @Singleton
    fun providesSecondString(): String = "This is the second string"

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FirstString

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SecondString
