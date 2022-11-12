package app.android.diguide

import android.app.Application
import app.android.diguide.di.activityModule
import app.android.diguide.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DIApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@DIApplication)
            modules(appModule, activityModule)
        }
    }
}


//TODO : Implement tests for Koin and Hilt [Unit tests, UI Tests, End to end Test]
