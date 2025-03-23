package prac.tanken.jsonplaceholder

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import prac.tanken.jsonplaceholder.di.appModule

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