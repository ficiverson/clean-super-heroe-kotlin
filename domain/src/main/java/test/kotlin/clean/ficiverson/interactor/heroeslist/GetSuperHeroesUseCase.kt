package test.kotlin.clean.ficiverson.interactor.heroeslist

import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.executor.CachePolicy
import test.kotlin.clean.ficiverson.executor.GetHeroeParams
import test.kotlin.clean.ficiverson.executor.UseCase
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract


class GetSuperHeroesUseCase(
    private val superHeroesRepositoryContract: SuperHeroesRepositoryContract
) : UseCase<GetHeroeParams, List<SuperHeroe>> {

    override suspend fun run(policy: CachePolicy, params: GetHeroeParams) =
        superHeroesRepositoryContract.getSuperHeroes(params, policy)
}