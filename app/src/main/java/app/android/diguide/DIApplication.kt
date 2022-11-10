package app.android.diguide

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DIApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}