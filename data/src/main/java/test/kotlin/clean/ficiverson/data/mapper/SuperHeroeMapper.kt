package test.kotlin.clean.ficiverson.data.mapper

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */

open class SuperHeroeMapper constructor() : Mapper<SuperHeroeEntity, SuperHeroe> {


    override fun mapFromEntity(type: SuperHeroeEntity): SuperHeroe {
        return SuperHeroe(type.name, type.title, type.avatar)
    }

    override fun mapToEntity(type: SuperHeroe): SuperHeroeEntity {
        return SuperHeroeEntity(type.name, type.title, type.avatar)
    }


}