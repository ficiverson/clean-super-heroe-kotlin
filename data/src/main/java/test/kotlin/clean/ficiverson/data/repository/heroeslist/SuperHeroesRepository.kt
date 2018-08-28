package test.kotlin.clean.ficiverson.data.repository.heroeslist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.executor.*

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */
class SuperHeroesRepository(
    private val localDataSource: SuperHeroeLocalDataSource,
    private val networkDataSource: SuperHeroeRemoteDataSource
) : SuperHeroesRepositoryContract {

    private val mapper: SuperHeroeMapper = SuperHeroeMapper()

    override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> {
        return when (policy) {
            NetworkOnly -> Success(networkDataSource.getAll()?.map { mapper.mapFromEntity(it) }.orEmpty())
            LocalOnly -> Success(localDataSource.getAll()?.map { mapper.mapFromEntity(it) }.orEmpty())
            NetworkAndStorage -> Success(networkDataSource.getAll()?.map { mapper.mapFromEntity(it) }.orEmpty())
            NoCache -> NoData()
        }
    }
}