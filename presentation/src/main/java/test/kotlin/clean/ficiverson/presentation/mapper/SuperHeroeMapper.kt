package test.kotlin.clean.ficiverson.presentation.mapper

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */

open class SuperHeroeMapper : Mapper<SuperHeroeView, SuperHeroe> {

    override fun mapToView(type: SuperHeroe): SuperHeroeView {
        return SuperHeroeView(type.name, type.title, type.avatar)
    }


}