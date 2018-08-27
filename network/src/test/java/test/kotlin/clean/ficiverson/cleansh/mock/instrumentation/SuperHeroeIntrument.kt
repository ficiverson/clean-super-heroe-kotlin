package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.network.model.SuperHeroeResponse

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeIntrument {

    companion object {
        fun givenASuperHeroeEntity() = SuperHeroeEntity("hi", "this", "http://google.com")
        fun givenASuperHeroeRemote() = SuperHeroeResponse("hi", "this", "http://google.com")
    }

}