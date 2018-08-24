package kotlin.clean.ficiverson.cache.mapper

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import kotlin.clean.ficiverson.cache.model.SuperHeroeDatabase

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
open class SuperHeroeEntityMapper : Mapper<SuperHeroeDatabase, SuperHeroeEntity> {

    override fun mapFromDatabase(type: SuperHeroeDatabase): SuperHeroeEntity {
        return SuperHeroeEntity(type.name, type.title, type.avatar)
    }

}