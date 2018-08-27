package test.kotlin.clean.ficiverson.network.heroelist

import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeRemoteDataSourceImpl : SuperHeroeRemoteDataSource {

    override fun getAll(): List<SuperHeroeEntity>? {
        return mutableListOf()
    }

}