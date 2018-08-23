package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
interface Invoker {

    fun <P, T> execute(
        useCase: UseCase<P, T>,
        params: Params,
        policy: CachePolicy = LocalOnly,
        onResult: (Result<T>) -> Unit
    )

    fun cancelAllAsync()

    fun isPendingTask(): Boolean
}