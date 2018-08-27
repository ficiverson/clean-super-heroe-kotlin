package test.kotlin.clean.ficiverson.cache.mapper

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
interface Mapper<in M, out E> {

    fun mapFromDatabase(type: M): E

}