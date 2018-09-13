package test.kotlin.clean.ficiverson.cleansh.injection

import android.app.Activity
import org.koin.android.ext.android.inject
import org.koin.dsl.module.applicationContext
import test.kotlin.clean.ficiverson.cache.heroelist.SuperHeroeLocalDataSourceImpl
import test.kotlin.clean.ficiverson.cleansh.login.MainRouter
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.repository.heroeslist.SuperHeroesRepository
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesPresenter
import test.kotlin.clean.ficiverson.presentation.jump.JumpPresenter
import test.kotlin.clean.ficiverson.presentation.login.MainPresenter
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract

inline fun <reified T> Activity.injectActivity(): Lazy<T> =
    inject(parameters = { mapOf(AppModules.ACTIVITY_PARAM to this) })


object AppModules {

    const val ACTIVITY_PARAM = "activity"

    val mainModule = applicationContext {
        factory { MainPresenter(it[ACTIVITY_PARAM]) }
        factory { MainRouter(it[ACTIVITY_PARAM]) }
    }

    val jumpModule = applicationContext {
        factory { JumpPresenter(it[ACTIVITY_PARAM]) }
    }

    val superHeroesModule = applicationContext {
        factory { SuperHeroeRemoteDataSourceImpl() as SuperHeroeRemoteDataSource }
        factory { SuperHeroeLocalDataSourceImpl() as SuperHeroeLocalDataSource }
        factory { SuperHeroesRepository(get(), get()) as SuperHeroesRepositoryContract }
        factory { GetSuperHeroesUseCase(get()) }
        factory { SuperHeroesPresenter(it[ACTIVITY_PARAM], get()) }
    }
}
