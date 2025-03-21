package com.marcgdiez.jsonplaceholder

import android.app.Application
import com.marcgdiez.jsonplaceholder.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JsonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@JsonApp)
            modules(
                appModule,
            )
        }
    }
}