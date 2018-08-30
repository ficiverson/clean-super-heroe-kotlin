package test.kotlin.clean.ficiverson.executor

//https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
class UseCaseInvoker : Invoker {

    private val asyncJobs: MutableList<Job> = mutableListOf()

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
                    LocalOnly -> onResult(asyncAwait { useCase.run(LocalOnly, params) })
                    NetworkOnly -> onResult(asyncAwait { useCase.run(NetworkOnly, params) })
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
    }

    private fun launchAsync(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = launch(UI) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
    }


    private suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return async(coroutineContext + CommonPool) { block() }
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
                for (useCase in useCases) {
                    results.add(async { useCase.run(policy, params) })
                }
                val resultsToNofify = mutableListOf<Result<T>>()
                for (result in results) {
                    resultsToNofify.add(result.await())
                }

                for (result in resultsToNofify) {
                    onResult(result)
                }

            } catch (e: Exception) {
                onResult(Error())
            }
        }
    }
}