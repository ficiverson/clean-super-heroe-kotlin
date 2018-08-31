package test.kotlin.clean.ficiverson.mock.instrumentation

import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import test.kotlin.clean.ficiverson.executor.*
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by f.souto.gonzalez on 30/08/2018.
 */
object InvokerInstruments {

    class TestContextProvider : CoroutineContextProvider() {
        override val Main: CoroutineContext = Unconfined
        override val Background: CoroutineContext = Unconfined
    }

    fun givenAnInvoker() = object : UseCaseInvoker(contextProvider = TestContextProvider()) {
        override fun <P, T> execute(useCase: UseCase<P, T>, params: P, policy: CachePolicy, onResult: (Result<T>) -> Unit) {
            asyncJobs.add(launch(contextProvider.Main) {
                onResult(useCase.run(LocalOnly, params))
            })
        }
    }

    fun givenAnInvokerAnCancelTasks() = object : UseCaseInvoker(TestContextProvider()) {
        override fun <P, T> execute(useCase: UseCase<P, T>, params: P, policy: CachePolicy, onResult: (Result<T>) -> Unit) {
            asyncJobs.add(launch() {
                delay(2000)
            })
        }
    }
}