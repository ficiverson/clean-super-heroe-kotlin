package test.kotlin.clean.ficiverson.cleansh.injection

import android.app.Activity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module.module
import test.kotlin.clean.ficiverson.cache.heroelist.SuperHeroeLocalDataSourceImpl
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.repository.heroeslist.SuperHeroesRepository
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl
import test.kotlin.clean.ficiverson.presentation.MainPresenter
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesPresenter
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract

inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> =
    inject { parametersOf(this) }


class AppModules {

    val mainModule = module {
        factory { MainPresenter(it[0]) }
    }

    val superHeroesModule = module {
        factory { SuperHeroeRemoteDataSourceImpl() as SuperHeroeRemoteDataSource }
        factory { SuperHeroeLocalDataSourceImpl() as SuperHeroeLocalDataSource }
        factory { SuperHeroesRepository(get(), get()) as SuperHeroesRepositoryContract }
        factory { GetSuperHeroesUseCase(get()) }
        factory { SuperHeroesPresenter(it[0], get()) }
    }
}
