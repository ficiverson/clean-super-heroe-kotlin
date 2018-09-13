package test.kotlin.clean.ficiverson.cleansh.login

import android.content.Context
import test.kotlin.clean.ficiverson.cleansh.navigator.BaseRouter
import test.kotlin.clean.ficiverson.cleansh.navigator.NoParams


/**
 * Created by f.souto.gonzalez on 10/09/2018.
 */

class MainRouter(override val context: Context) : BaseRouter<NoParams> {
    companion object {
        const val LOGIN = "login"
        const val HEROE_LIST = "heroesList"
        const val JUMP = "jump"
    }
}