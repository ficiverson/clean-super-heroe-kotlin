package kotlin.clean.ficiverson.network.mapper

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import kotlin.clean.ficiverson.network.model.SuperHeroeResponse

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
open class SuperHeroeEntityMapper : Mapper<SuperHeroeResponse, SuperHeroeEntity> {
    
    override fun mapFromRemote(type: SuperHeroeResponse): SuperHeroeEntity {
        return SuperHeroeEntity(type.name, type.title, type.avatar)
    }

}