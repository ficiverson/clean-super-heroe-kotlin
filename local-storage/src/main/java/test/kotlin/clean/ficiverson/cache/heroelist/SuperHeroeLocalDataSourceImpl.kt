package test.kotlin.clean.ficiverson.cache.heroelist

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import test.kotlin.clean.ficiverson.cache.database.SuperHeroesDatabase
import test.kotlin.clean.ficiverson.cache.mapper.SuperHeroeEntityMapper
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeLocalDataSourceImpl : SuperHeroeLocalDataSource, KoinComponent {

    private val database: SuperHeroesDatabase by inject()
    private val mapper = SuperHeroeEntityMapper()

    @Throws(Exception::class)
    override fun setAll(superHeroes: List<SuperHeroeEntity>) {
        // TODO: avoid this forEach

        superHeroes.map {
            mapper.mapToDatabase(it)
        }.forEach {
            database.daoAccess().insertSuperHeroe(it)
        }
    }

    @Throws(Exception::class)
    override fun getAll(): List<SuperHeroeEntity>? {
        return database.daoAccess().getAllSuperHeroes().map {
            mapper.mapFromDatabase(it)
        }
    }

}