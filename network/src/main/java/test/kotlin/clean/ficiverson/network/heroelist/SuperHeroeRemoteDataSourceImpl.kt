package test.kotlin.clean.ficiverson.network.heroelist

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.network.SuperHeroesApi
import test.kotlin.clean.ficiverson.network.mapper.SuperHeroeEntityMapper

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeRemoteDataSourceImpl : SuperHeroeRemoteDataSource, KoinComponent {

    private val api: SuperHeroesApi by inject()
    private val mapper = SuperHeroeEntityMapper()

    @Throws(Exception::class)
    override fun getAll(): List<SuperHeroeEntity>? {
        val response = api.getHeroes().execute()
        return if (response.isSuccessful) {
            response.body()?.superheroes?.map {
                mapper.mapFromRemote(it)
            } ?: emptyList()
        } else {
            throw Exception()
        }
    }
}