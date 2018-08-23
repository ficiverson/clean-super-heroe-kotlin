package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 22/08/2018.
 */
sealed class Params()

data class GetHeroeParams(private val page: Int) : Params()