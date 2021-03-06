package test.kotlin.clean.ficiverson.cleansh

import android.app.Application
import org.koin.android.ext.android.startKoin
import test.kotlin.clean.ficiverson.cache.injection.LocalStorageModules
import test.kotlin.clean.ficiverson.cleansh.injection.AppModules
import test.kotlin.clean.ficiverson.network.injection.NetworkModules


class HeroesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinModules()
    }

    private fun initKoinModules() {
        val appModules = AppModules()
        val networkModules = NetworkModules("https://api.myjson.com/")
        val localStorageModules = LocalStorageModules(this)

        startKoin(
            this,
            listOf(
                appModules.mainModule,
                appModules.superHeroesModule,
                networkModules.networkModule,
                localStorageModules.localStorageModule
            )
        )
    }
}
