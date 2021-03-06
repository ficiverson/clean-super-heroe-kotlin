package test.kotlin.clean.ficiverson.data.datasource.heroelist

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */
interface SuperHeroeLocalDataSource {

    fun setAll(superHeroes: List<SuperHeroeEntity>) = Unit

    fun getAll(): List<SuperHeroeEntity>?
}