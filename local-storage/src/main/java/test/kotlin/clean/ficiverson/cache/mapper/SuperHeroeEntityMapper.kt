package test.kotlin.clean.ficiverson.cache.mapper

import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
open class SuperHeroeEntityMapper : Mapper<SuperHeroeDatabaseEntity, SuperHeroeEntity> {

    override fun mapFromDatabase(type: SuperHeroeDatabaseEntity): SuperHeroeEntity {
        return SuperHeroeEntity(type.name, type.title, type.avatar)
    }

    override fun mapToDatabase(type: SuperHeroeEntity): SuperHeroeDatabaseEntity {
        return SuperHeroeDatabaseEntity(type.name, type.title, type.avatar)
    }
}