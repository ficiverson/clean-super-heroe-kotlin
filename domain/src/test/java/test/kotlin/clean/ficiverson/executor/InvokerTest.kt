package test.kotlin.clean.ficiverson.executor

import kotlinx.coroutines.experimental.launch
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class InvokerTest {

    @Test
    fun `that can create a use case invoker`() {
        val invoker = object : Invoker {
            override fun <P, T> execute(useCase: UseCase<P, T>, params: P, policy: CachePolicy, onResult: (Result<T>) -> Unit) {
                launch {
                    onResult(useCase.run(LocalOnly, params))
                }
            }

            override fun cancelAllAsync() {
                //Nothing to do
            }

            override fun isPendingTask(): Boolean {
                return false
            }
        }
        val useCase = object : UseCase<Unit, String> {
            override suspend fun run(policy: CachePolicy, params: Unit): Result<String> = Success("Awesome Result")
        }
        val params = Unit

        invoker.execute(
            useCase,
            params,
            LocalOnly,
            ::onResult
        )
        //
    }

    fun onResult(result: Result<String>) {
        assertThat(result is Success).isTrue()
        assertThat((result as Success).data).isEqualTo("Awesome Result")
    }
}
