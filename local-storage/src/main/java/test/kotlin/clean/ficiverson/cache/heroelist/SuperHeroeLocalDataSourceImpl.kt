package test.kotlin.clean.ficiverson.cache.heroelist

import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeLocalDataSourceImpl : SuperHeroeLocalDataSource {

    override fun getAll(): List<SuperHeroeEntity>? {
        return mutableListOf()
    }

}