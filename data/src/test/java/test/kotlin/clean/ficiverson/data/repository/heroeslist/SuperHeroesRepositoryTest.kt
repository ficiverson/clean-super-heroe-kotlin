package test.kotlin.clean.ficiverson.data.repository.heroeslist

import org.assertj.core.api.Assertions.assertThat
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

    private var localDataSource = object : SuperHeroeLocalDataSource {
        override fun getAll() = mutableListOf<SuperHeroeEntity>()
    }
    private var remoteDataSource = object : SuperHeroeRemoteDataSource {
        override fun getAll() = mutableListOf<SuperHeroeEntity>()
    }

    private var superHeroesRepository = SuperHeroesRepository(localDataSource, remoteDataSource)

    @Test
    fun `that can get superheroes from remote server`() {
        val result = superHeroesRepository.getSuperHeroes(GetHeroeParams(1), NetworkOnly) as Success
        assertThat(result.data.size).isEqualTo(0)
    }

    @Test
    fun `that can get superheroes from local storage`() {
        val result = superHeroesRepository.getSuperHeroes(GetHeroeParams(1), LocalOnly) as Success
        assertThat(result.data.size).isEqualTo(0)
    }

    @Test
    fun `that can  get a nodata response`() {
        val result = superHeroesRepository.getSuperHeroes(GetHeroeParams(1), NoCache) as NoData
        assertThat(result.error).isNotNull()
    }
}