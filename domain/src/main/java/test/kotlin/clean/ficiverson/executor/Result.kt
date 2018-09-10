package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 21/08/2018.
 */
sealed class Result<out T>

data class Success<T>(val data: T) : Result<T>()
data class NoInternetError(val error: Exception = Exception(), val message: String? = null) : Result<Nothing>()
data class Error(val error: Exception = Exception(), val message: String? = null) : Result<Nothing>()
data class NoData(val error: Exception = Exception()) : Result<Nothing>()

