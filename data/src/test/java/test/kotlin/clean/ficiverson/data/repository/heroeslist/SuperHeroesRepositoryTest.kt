package test.kotlin.clean.ficiverson.data.repository.heroeslist

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.executor.*

/**
 * Created by f.souto.gonzalez on 03/09/2018.
 */
class SuperHeroesRepositoryTest {
    lateinit var localDataSource: SuperHeroeLocalDataSource
    lateinit var remoteDataSource: SuperHeroeRemoteDataSource

    @Before
    fun before() {
        localDataSource = object : SuperHeroeLocalDataSource {
            override fun getAll(): List<SuperHeroeEntity>? {
                return mutableListOf<SuperHeroeEntity>()
            }

            override fun setAll(superHeroes: List<SuperHeroeEntity>) {
                //nothing to do
            }

        }

        remoteDataSource = object : SuperHeroeRemoteDataSource {
            override fun getAll(): List<SuperHeroeEntity>? {
                return mutableListOf<SuperHeroeEntity>()
            }

        }
    }

    @Test
    fun `that can get superheroes from remote server`() {
        val superHeroesRepository = SuperHeroesRepository(localDataSource, remoteDataSource)
        val result = superHeroesRepository.getSuperHeroes(params = GetHeroeParams(page = 1), policy = NetworkOnly) as Success
        Assertions.assertThat(result.data.size).isEqualTo(0)
    }


    @Test
    fun `that can get superheroes from local storage`() {
        val superHeroesRepository = SuperHeroesRepository(localDataSource, remoteDataSource)
        val result = superHeroesRepository.getSuperHeroes(params = GetHeroeParams(page = 1), policy = LocalOnly) as Success
        Assertions.assertThat(result.data.size).isEqualTo(0)
    }

    @Test
    fun `that can  get a nodata response`() {
        val superHeroesRepository = SuperHeroesRepository(localDataSource, remoteDataSource)
        val result = superHeroesRepository.getSuperHeroes(params = GetHeroeParams(page = 1), policy = NoCache) as NoData
        Assertions.assertThat(result.error).isNotNull()
    }
}