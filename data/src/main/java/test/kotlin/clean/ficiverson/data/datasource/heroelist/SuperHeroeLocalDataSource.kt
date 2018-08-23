package test.kotlin.clean.ficiverson.data.datasource.heroelist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.executor.Result

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */
interface SuperHeroeLocalDataSource {

    fun getAll(): List<SuperHeroeEntity>

}