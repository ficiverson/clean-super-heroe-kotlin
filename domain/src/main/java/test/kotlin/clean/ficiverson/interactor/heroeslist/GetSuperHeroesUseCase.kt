package test.kotlin.clean.ficiverson.interactor.heroeslist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract
import test.kotlin.clean.ficiverson.executor.CachePolicy
import test.kotlin.clean.ficiverson.executor.GetHeroeParams
import test.kotlin.clean.ficiverson.executor.Params
import test.kotlin.clean.ficiverson.executor.UseCase


class GetSuperHeroesUseCase(
    private val superHeroesRepositoryContract: SuperHeroesRepositoryContract
) : UseCase<GetHeroeParams, List<SuperHeroe>> {

    override suspend fun run(policy: CachePolicy, params: Params) =
        superHeroesRepositoryContract.getSuperHeroes(params, policy)
}