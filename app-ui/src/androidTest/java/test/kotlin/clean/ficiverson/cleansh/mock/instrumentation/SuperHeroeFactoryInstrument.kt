package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import test.kotlin.clean.ficiverson.model.SuperHeroe

object SuperHeroeFactoryInstrument {
    fun givenASuperHeroe(name : String) = SuperHeroe(name, "haosdj", "avatar")
}