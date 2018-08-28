package test.kotlin.clean.ficiverson.executor

interface UseCase<P, T> {
    suspend fun run(policy: CachePolicy = LocalOnly, params: P): Result<T>
}