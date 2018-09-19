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
        override val main: CoroutineContext = Unconfined
        override val background: CoroutineContext = Unconfined
    }

    fun givenAnInvoker() = object : UseCaseInvoker(TestContextProvider()) {
        override fun <P, T> execute(vararg useCases: UseCaseExecutor<P, T>, isParallel: Boolean, onResult: ((Result<T>) -> Unit)?) {
            asyncJobs.add(launch(contextProvider.main) {
                useCases.forEach {
                    onResult?.invoke(it.executeUseCase())
                }
            })

        }
    }

    fun givenAnInvokerAnCancelTasks() = object : UseCaseInvoker(TestContextProvider()) {
        override fun <P, T> execute(vararg useCases: UseCaseExecutor<P, T>, isParallel: Boolean, onResult: ((Result<T>) -> Unit)?) {
            asyncJobs.add(launch {
                delay(2000)
            })
        }
    }
}