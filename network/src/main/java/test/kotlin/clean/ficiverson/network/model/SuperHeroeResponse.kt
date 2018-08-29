package test.kotlin.clean.ficiverson.network.model

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */

data class SuperHeroeWrapperResponse(
    val superheroes: List<SuperHeroeResponse>
)

data class SuperHeroeResponse(
    val name: String,
    val photo: String,
    val realName: String,
    val height: String,
    val power: String
)