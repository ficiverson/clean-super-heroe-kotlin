package test.kotlin.clean.ficiverson.network.mapper

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.network.model.SuperHeroeResponse

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeEntityMapper : Mapper<SuperHeroeResponse, SuperHeroeEntity> {

    override fun mapFromRemote(type: SuperHeroeResponse): SuperHeroeEntity =
        SuperHeroeEntity(type.name, type.realName, type.photo)
}