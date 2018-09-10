package test.kotlin.clean.ficiverson.cache.herolist

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.loadKoinModules
import test.kotlin.clean.ficiverson.cache.database.SuperHeroesDatabase
import test.kotlin.clean.ficiverson.cache.heroelist.SuperHeroeLocalDataSourceImpl
import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
@RunWith(AndroidJUnit4::class)
open class SuperHeroeLocalDataSourceImplTest {

    private lateinit var heroesDatabase: SuperHeroesDatabase

    @Before
    fun initDb() {
        heroesDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            SuperHeroesDatabase::class.java
        ).allowMainThreadQueries().build()

        loadKoinModules(listOf(applicationContext {
            bean { heroesDatabase }
        }))

    }

    @After
    fun closeDb() {
        heroesDatabase.close()
        StandAloneContext.closeKoin()
    }

    @Test
    fun thatCanHandleALocalAccess() {
        val superHeroeLocalDataSource = SuperHeroeLocalDataSourceImpl()
        heroesDatabase.daoAccess().insertSuperHeroe(SuperHeroeDatabaseEntity("paco", "spider", "http://google.com"))
        Assertions.assertThat(superHeroeLocalDataSource.getAll()?.size).isEqualTo(1)
    }

    @Test
    fun thatCanHandleALocalAccessToInsert() {
        val superHeroeLocalDataSource = SuperHeroeLocalDataSourceImpl()
        superHeroeLocalDataSource.setAll(mutableListOf())
        Assertions.assertThat(superHeroeLocalDataSource.getAll()?.size).isEqualTo(0)
    }
}
