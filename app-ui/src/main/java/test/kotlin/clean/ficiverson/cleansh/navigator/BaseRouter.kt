package test.kotlin.clean.ficiverson.cleansh.navigator

import android.content.Context
import android.content.Intent
import java.io.Serializable

/**
 * Created by f.souto.gonzalez on 11/09/2018.
 */
interface BaseRouter<P : Serializable> {
    val context: Context

    fun intentData(data: P? = null, vararg routes: String): Array<out Intent>? = IntentData.createNavigation<P>(data, context, *routes)

    fun navigate(intents: Array<out Intent>? = null) {
        context.startActivities(intents)
    }

    fun goToScreen(vararg routes: String, func: (Any.() -> P?)? = null) {
        navigate(intentData(func?.invoke(NoParams()), *routes))
    }

}

class NoParams : Serializable