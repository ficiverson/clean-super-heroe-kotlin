package test.kotlin.clean.ficiverson.mock.instrumentation

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import test.kotlin.clean.ficiverson.executor.*

/**
 * Created by f.souto.gonzalez on 30/08/2018.
 */
object InvokerInstruments {
    fun givenAnInvoker() = object : UseCaseInvoker() {
        override fun <P, T> execute(useCase: UseCase<P, T>, params: P, policy: CachePolicy, onResult: (Result<T>) -> Unit) {
            runBlocking {
                asyncJobs.add(launch(coroutineContext) {
                    onResult(useCase.run(LocalOnly, params))
                })
            }
        }
    }

    fun givenAnInvokerAnCancelTasks() = object : UseCaseInvoker() {
        override fun <P, T> execute(useCase: UseCase<P, T>, params: P, policy: CachePolicy, onResult: (Result<T>) -> Unit) {
            asyncJobs.add(launch {
                delay(2000)
            })
        }
    }
}