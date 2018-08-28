package test.kotlin.clean.ficiverson.interactor.heroeslist

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.junit.Test
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.repository.SuperHeroesRepositoryContract

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class InvokerTest {

    @Test
    fun `that can create a use case invoker`() {
        val useCase = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
            override fun getSuperHeroes(params: GetHeroeParams, policy: CachePolicy): Result<List<SuperHeroe>> =
                Success(emptyList())
        })
        runBlocking {
            assertThat(useCase.run(LocalOnly, GetHeroeParams(page = 1))).isNotNull()
        }
    }

}