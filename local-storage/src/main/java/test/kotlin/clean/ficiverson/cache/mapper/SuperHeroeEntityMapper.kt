package test.kotlin.clean.ficiverson.cache.mapper

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
open class SuperHeroeEntityMapper : Mapper<SuperHeroeDatabaseEntity, SuperHeroeEntity> {

    override fun mapFromDatabase(type: SuperHeroeDatabaseEntity): SuperHeroeEntity {
        return SuperHeroeEntity(type.name, type.title, type.avatar)
    }

}