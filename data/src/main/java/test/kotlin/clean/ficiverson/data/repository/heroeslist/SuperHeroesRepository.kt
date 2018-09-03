package test.kotlin.clean.ficiverson.data.repository.heroeslist

import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract
import java.io.IOException

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */
class SuperHeroesRepository(
    internal val localDataSource: SuperHeroeLocalDataSource,
    internal val networkDataSource: SuperHeroeRemoteDataSource
) : SuperHeroesRepositoryContract {

    private val mapper: SuperHeroeMapper = SuperHeroeMapper()

    override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy) =
        when (policy) {
            LocalOnly -> getSuperHeroesFromLocalStorage()
            NetworkOnly -> getSuperHeroesFromNetwork()
            else -> NoData()
        }

    private fun getSuperHeroesFromLocalStorage(): Result<List<SuperHeroe>> =
        try {
            Success(localDataSource.getAll()?.map { mapper.mapFromEntity(it) }.orEmpty())
        } catch (e: Exception) {
            Error(e)
        }

    private fun getSuperHeroesFromNetwork(): Result<List<SuperHeroe>> =
        try {
            Success(networkDataSource.getAll()?.map { mapper.mapFromEntity(it) }.orEmpty())
        } catch (e: IOException) {
            NoInternetError(e)
        } catch (e: Exception) {
            Error(e)
        }
}