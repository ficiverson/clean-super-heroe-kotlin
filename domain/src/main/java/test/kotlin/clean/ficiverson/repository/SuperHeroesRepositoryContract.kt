package test.kotlin.clean.ficiverson.repository

import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.executor.CachePolicy
import test.kotlin.clean.ficiverson.executor.GetHeroeParams
import test.kotlin.clean.ficiverson.executor.Result

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface SuperHeroesRepositoryContract {
    fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>>
}
