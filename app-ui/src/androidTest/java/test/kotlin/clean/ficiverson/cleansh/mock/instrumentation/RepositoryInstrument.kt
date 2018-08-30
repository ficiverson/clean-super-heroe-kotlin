package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.SuperHeroeFactoryInstrument.givenASuperHeroe
import test.kotlin.clean.ficiverson.executor.CachePolicy
import test.kotlin.clean.ficiverson.executor.GetHeroeParams
import test.kotlin.clean.ficiverson.executor.Result
import test.kotlin.clean.ficiverson.executor.Success
import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract

object RepositoryInstrument {

    fun givenARepository( name : String) = object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
            Success<List<SuperHeroe>>(mutableListOf(givenASuperHeroe(name),givenASuperHeroe(name)))
    }
}