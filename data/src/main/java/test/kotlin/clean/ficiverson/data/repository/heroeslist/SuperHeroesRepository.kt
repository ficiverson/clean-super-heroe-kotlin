package test.kotlin.clean.ficiverson.data.repository.heroeslist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.buffer.android.boilerplate.domain.repository.SuperHeroesRepositoryContract
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

    val mapper: SuperHeroeMapper = SuperHeroeMapper()

    override fun getSuperHeroes(params: Params, policy: CachePolicy): Result<List<SuperHeroe>> {
        return when (policy) {
            NetworkOnly -> {
                Success(data = networkDataSource.getAll()?.let {
                    it.map { mapper.mapFromEntity(it) }
                }) as Result<List<SuperHeroe>>
            }

            LocalOnly -> {
                Success(data = localDataSource.getAll()?.let {
                    it.map { mapper.mapFromEntity(it) }
                }) as Result<List<SuperHeroe>>
            }

            NetworkAndStorage -> {
                Success(data = networkDataSource.getAll()?.let {
                    it.map { mapper.mapFromEntity(it) }
                }) as Result<List<SuperHeroe>>
            }

            NoCache -> NoData()
        }
        return NoData()

    }

}