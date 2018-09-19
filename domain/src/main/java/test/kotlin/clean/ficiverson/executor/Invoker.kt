package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 20/08/2018.
 */
interface Invoker {

    fun <P, T> execute(
        vararg useCases: UseCaseExecutor<P, T>,
        isParallel: Boolean = false,
        onResult: ((Result<T>) -> Unit)?
    )

    fun cancelAllAsync()

    fun isPendingTask(): Boolean
}