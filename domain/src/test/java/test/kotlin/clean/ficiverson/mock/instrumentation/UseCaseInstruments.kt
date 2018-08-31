package test.kotlin.clean.ficiverson.mock.instrumentation

import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract

/**
 * Created by f.souto.gonzalez on 30/08/2018.
 */
object UseCaseInstruments {

    fun givenAGenericSuccessResultUseCase() = object : UseCase<Unit, String> {
        override suspend fun run(policy: CachePolicy, params: Unit): Result<String> = Success("Awesome Result")
    }

    fun givenASuccessResult() = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
            Success(emptyList())
    })

    fun givenAErrorResult() = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
            Error()
    })

    fun givenANetworkErrorResult() = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
            NoInternetError()
    })

    fun givenANoDataErrorResult() = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
            NoData()
    })
}