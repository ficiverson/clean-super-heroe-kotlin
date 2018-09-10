package test.kotlin.clean.ficiverson.cache.mock.instrumentation

import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeIntrument {

    companion object {
        fun givenASuperHeroeEntity() = SuperHeroeEntity("hi", "this", "http://google.com")
        fun givenASuperHeroeLocal() = SuperHeroeDatabaseEntity("hi", "this", "http://google.com")
    }

}