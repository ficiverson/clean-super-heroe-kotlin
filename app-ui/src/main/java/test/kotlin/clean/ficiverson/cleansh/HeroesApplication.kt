package test.kotlin.clean.ficiverson.cleansh

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import test.kotlin.clean.ficiverson.presentation.AppModules


class HeroesApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModules().module))
    }
}
