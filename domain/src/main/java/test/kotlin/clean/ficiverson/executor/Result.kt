package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 21/08/2018.
 */
sealed class Result<T> {
    var status: CachePolicy = NoCache
}

data class Success<T>(val data: T) : Result<T>()
data class NoInternetError<T>(val error: Exception = Exception(), val message: String? = null) : Result<T>()
data class Error<T>(val error: Exception = Exception(), val message: String? = null) : Result<T>()
data class NoData<T>(val error: Exception = Exception()) : Result<T>()

