package test.kotlin.clean.ficiverson.executor

//https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
class UseCaseInvoker : Invoker {


    val asyncJobs: MutableList<Job> = mutableListOf()

    override fun isPendingTask(): Boolean {
        return asyncJobs.size != 0
    }

    override fun <P, T> execute(
        useCase: UseCase<P, T>,
        params: Params,
        policy: CachePolicy,
        onResult: (Result<T>) -> Unit
    ) {
        launchAsync {
            try {
                when(policy){
                    LocalOnly, NetworkAndStorage -> onResult(asyncAwait { useCase.run(policy, params) })
                    NetworkOnly, NetworkAndStorage -> onResult(asyncAwait { useCase.run(policy, params) })
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

    //review paraller executions
    fun <P, T : Any> executeParallel(
        useCases: List<UseCase<P, T>>,
        params: Params,
        policy: CachePolicy,
        onResult: (Result<T>) -> Unit
    ) {

        launchAsync {
            try {
                var results = mutableListOf<Deferred<Result<T>>>()
                for (useCase in useCases) {
                    results.add(async { useCase.run(policy, params) })
                }
                var resultsToNofify = mutableListOf<Result<T>>()
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