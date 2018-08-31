package test.kotlin.clean.ficiverson.executor

//https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
open class UseCaseInvoker(internal val contextProvider : CoroutineContextProvider = CoroutineContextProvider()) : Invoker {

    internal val asyncJobs: MutableList<Job> = mutableListOf()

    override fun isPendingTask(): Boolean = asyncJobs.size != 0

    override fun <P, T> execute(
        useCase: UseCase<P, T>,
        params: P,
        policy: CachePolicy,
        onResult: (Result<T>) -> Unit
    ) {
        launchAsync {
            try {
                when (policy) {
                    LocalOnly, NetworkOnly -> onResult(asyncAwait { useCase.run(policy, params) })
                    NetworkAndStorage -> {
                        onResult(asyncAwait { useCase.run(LocalOnly, params) })
                        onResult(asyncAwait { useCase.run(NetworkOnly, params) })
                    }
                }
            } catch (e: Exception) {
                onResult(Error())
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
        val job: Job = launch(contextProvider.Main) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
    }


    private suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return async(coroutineContext + contextProvider.Background) { block() }
    }

    private suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
        return async(block).await()
    }

    //TODO review parallel executions
    fun <P, T : Any> executeParallel(
        useCases: List<UseCase<P, T>>,
        params: P,
        policy: CachePolicy,
        onResult: (Result<T>) -> Unit
    ) {

        launchAsync {
            try {
                val results = mutableListOf<Deferred<Result<T>>>()
                useCases.forEach {
                    when (policy) {
                        LocalOnly, NetworkOnly -> results.add(async { it.run(policy, params) })
                        NetworkAndStorage -> {
                            results.add(async { it.run(LocalOnly, params) })
                            results.add(async { it.run(NetworkOnly, params) })
                        }
                    }
                }
                val resultsToNotify = mutableListOf<Result<T>>()
                results.forEach {
                    resultsToNotify.add(it.await())
                }
                resultsToNotify.forEach {
                    onResult(it)
                }
            } catch (e: Exception) {
                onResult(Error())
            }
        }
    }
}

open class CoroutineContextProvider() {
    open val Main: CoroutineContext by lazy { UI }
    open val Background: CoroutineContext by lazy { CommonPool }
}