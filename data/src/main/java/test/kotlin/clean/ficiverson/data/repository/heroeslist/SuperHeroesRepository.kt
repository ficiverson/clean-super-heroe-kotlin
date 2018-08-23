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
    private val networkDataSource: SuperHeroeRemoteDataSource) : SuperHeroesRepositoryContract {

    val mapper: SuperHeroeMapper = SuperHeroeMapper()

    override fun getSuperHeroes(params: Params, policy: CachePolicy): Result<List<SuperHeroe>> {
//        return when (policy) {
//            NetworkFirst -> {
//
//                val resultList: List<SuperHeroe> = networkDataSource.getAll()?.also {
//                    Success(data = it.map { mapper.mapFromEntity(it) }).apply{
//                        status = policy
//                    }
//                }
//
//            }
//
//            LocalFirst -> localDataSource.getAll()
//
//            LocalOnly -> localDataSource.getAll()
//
//            NetworkOnly -> networkDataSource.getAll()
//
//            NoCache -> NoData()
//        }
        return NoData()

    }

}