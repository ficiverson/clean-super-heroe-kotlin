package test.kotlin.clean.ficiverson.interactor.heroeslist

import kotlinx.coroutines.experimental.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.mock.instrumentation.UseCaseInstruments.givenAErrorResult
import test.kotlin.clean.ficiverson.mock.instrumentation.UseCaseInstruments.givenANetworkErrorResult
import test.kotlin.clean.ficiverson.mock.instrumentation.UseCaseInstruments.givenANoDataErrorResult
import test.kotlin.clean.ficiverson.mock.instrumentation.UseCaseInstruments.givenASuccessResult

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class InvokerTest {

    @Test
    fun `that can invoke a use case with success`() {
        val useCase = givenASuccessResult()
        runBlocking {
            val result = useCase.run(LocalOnly, GetHeroeParams(1))
            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
        }
    }

    @Test
    fun `that can invoke a use case with error`() {
        val useCase = givenAErrorResult()
        runBlocking {
            val result = useCase.run(LocalOnly, GetHeroeParams(1))
            assertThat(result).isNotNull()
            assertThat(result is Error).isTrue()
        }
    }

    @Test
    fun `that can invoke a use case with network error`() {
        val useCase = givenANetworkErrorResult()
        runBlocking {
            val result = useCase.run(LocalOnly, GetHeroeParams(1))
            assertThat(result).isNotNull()
            assertThat(result is NoInternetError).isTrue()
        }
    }

    @Test
    fun `that can invoke a use case with no data error`() {
        val useCase = givenANoDataErrorResult()
        runBlocking {
            val result = useCase.run(LocalOnly, GetHeroeParams(1))
            assertThat(result).isNotNull()
            assertThat(result is NoData).isTrue()
        }
    }


}