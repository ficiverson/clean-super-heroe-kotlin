package test.kotlin.clean.ficiverson.presentation

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.buffer.android.boilerplate.domain.repository.SuperHeroesRepositoryContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListPresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListViewTranslator
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView

class AppModules {

    val module: Module = applicationContext {
        factory { viewTranslator as SuperHeroeListViewTranslator}
        factory { repository as SuperHeroesRepositoryContract }
        factory { GetSuperHeroesUseCase(get()) }
        factory { UseCaseInvoker() }
        factory { SuperHeroeMapper() }
        factory { SuperHeroeListPresenter(get(), get(), get(), get()) }
//        bean { retrofit(get()) }
//        bean { okHttpClient() }
    }

    private val viewTranslator = object : SuperHeroeListViewTranslator {
        override fun showData(data: List<SuperHeroeView>) = Unit

        override fun showErrorState() = Unit
    }

    private val repository = object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: Params, policy: CachePolicy): Result<List<SuperHeroe>> =
            Success(emptyList())
    }

//    private fun stationApi(retrofit: Retrofit): StationApi =
//        retrofit.create(StationApi::class.java)
//
//    private fun retrofit(okHttpClient: OkHttpClient) =
//        Retrofit.Builder()
//            .baseUrl("http://servizos.meteogalicia.gal/rss/observacion/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    private fun okHttpClient() =
//        OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor { message -> Log.d("Interceptor", message) }.apply { level = HttpLoggingInterceptor.Level.BODY })
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
}