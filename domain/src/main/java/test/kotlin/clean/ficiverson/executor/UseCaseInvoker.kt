package test.kotlin.clean.ficiverson.executor

//https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
open class UseCaseInvoker(internal val contextProvider: CoroutineContextProvider = CoroutineContextProvider()) : Invoker {

    internal val asyncJobs: MutableList<Job> = mutableListOf()

    override fun isPendingTask(): Boolean = asyncJobs.size != 0

    override fun <P, T> execute(
        vararg useCases: UseCaseExecutor<P, T>,
        isParallel: Boolean,
        onResult: ((Result<T>) -> Unit)?
    ) {
        launchAsync {
            try {
                executeList(*useCases, isParallel = isParallel).run {
                    forEach {
                        onResult?.invoke(it)
                    }
                }
            } catch (e: Exception) {
                onResult?.invoke(Error())
            }
        }
    }

    override fun cancelAllAsync() {
        val asyncJobsSize = asyncJobs.size

        if (asyncJobsSize > 0) {
            for (i in 0 until asyncJobsSize) {
                asyncJobs[i].cancel()
            }
        }
        asyncJobs.clear()
    }

    private fun launchAsync(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = launch(contextProvider.main) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { _ ->
            job.takeUnless { it.isCancelled }?.run {
                asyncJobs.remove(this)
            }
        }
    }


    private suspend fun <T> async(isParallel: Boolean, block: suspend CoroutineScope.() -> T): Deferred<T> {
        return async(
            coroutineContext + contextProvider.background,
            start = if (isParallel) CoroutineStart.DEFAULT else CoroutineStart.LAZY
        ) {
            block()
        }
    }

    private suspend fun <P, T> executeList(vararg executorList: UseCaseExecutor<P, T>, isParallel: Boolean): List<Result<T>> {
        val results = mutableListOf<Deferred<Result<T>>>()
        executorList.forEach {
            when (it.policy) {
                LocalOnly, NetworkOnly -> results.add(async(isParallel, { it.executeUseCase() }))
                NetworkAndStorage -> {
                    results.add(async(isParallel, { it.executeUseCase(LocalOnly) }))
                    results.add(async(isParallel, { it.executeUseCase(NetworkOnly) }))
                }
            }
        }

        return results.map {
            if (!isParallel) it.start()
            it.await()
        }
    }

}

class UseCaseExecutor<P, T>(
    private val useCase: UseCase<P, T>,
    private val params: P,
    val policy: CachePolicy = NetworkAndStorage
) {
    suspend fun executeUseCase(_policy: CachePolicy = policy) = useCase.run(_policy, params)
}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
    open val background: CoroutineContext by lazy { DefaultDispatcher }
}