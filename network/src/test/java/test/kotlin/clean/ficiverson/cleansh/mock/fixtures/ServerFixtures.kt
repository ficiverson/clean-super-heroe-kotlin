package test.kotlin.clean.ficiverson.cleansh.mock.fixtures

import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.MockServer
import java.io.IOException

/**
 * Created by f.souto.gonzalez on 03/09/2018.
 */
object ServerFixtures {

    //super heroes getall
    val HEROES_LIST = "getSuperHeroes.json"

    @Throws(IOException::class)
    fun enqueueServerFile(server: MockServer, file: String) {
        server.enqueueFile(200, file)
    }

    fun enqueueServerError(server: MockServer, code: Int) {
        server.enqueue(code)
    }
}