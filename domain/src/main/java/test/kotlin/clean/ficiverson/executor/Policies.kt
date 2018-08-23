package test.kotlin.clean.ficiverson.executor

/**
 * Created by f.souto.gonzalez on 21/08/2018.
 */

sealed class CachePolicy {

}

object NoCache : CachePolicy()
object LocalOnly : CachePolicy()
object NetworkOnly : CachePolicy()
object NetworkAndStorage : CachePolicy()