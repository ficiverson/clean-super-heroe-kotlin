package test.kotlin.clean.ficiverson.presentation

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.buffer.android.boilerplate.domain.repository.SuperHeroesRepositoryContract
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListPresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListViewTranslator
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView

class AppModules {

    val module = Kodein {
        bind<SuperHeroeListViewTranslator>() with provider { viewTranslator }
        bind<SuperHeroesRepositoryContract>() with provider { repository }
        bind<GetSuperHeroesUseCase>() with provider { GetSuperHeroesUseCase(instance()) }
        bind<UseCaseInvoker>() with provider { UseCaseInvoker() }
        bind<SuperHeroeMapper>() with provider { SuperHeroeMapper() }
        bind<SuperHeroeListPresenter>() with provider { SuperHeroeListPresenter(instance(), instance(), instance(), instance()) }
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