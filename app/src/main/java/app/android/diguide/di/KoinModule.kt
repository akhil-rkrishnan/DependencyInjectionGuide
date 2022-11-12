package app.android.diguide.di

import app.android.diguide.presentation.MainActivity
import app.android.diguide.presentation.MainViewModel
import app.android.diguide.repository.ApiInterface
import app.android.diguide.repository.MainRepository
import app.android.diguide.repository.MainRepositoryImpl
import app.android.diguide.utils.BASE_URL
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
// we don't need to put annotations while using koin the implementation is similar as hilt @Module -> module {} @Singleton -> singleton {}
val appModule = module {
    // singleton
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    viewModel {
        MainViewModel(get())
    }

}

val activityModule = module {
  scope<MainActivity> {
      // use qualifiers to return multiple instances of same data type.
      // To differentiate the same data type, other wise koin is confused which data type to return
      scoped(qualifier = named("firstString")) { "hello" }
      scoped(qualifier = named("secondString")) { "world" }
  }
}