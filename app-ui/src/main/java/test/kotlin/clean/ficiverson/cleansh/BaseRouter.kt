package test.kotlin.clean.ficiverson.cleansh

import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

/**
 * Created by f.souto.gonzalez on 11/09/2018.
 */
interface BaseRouter<P> {
    val context: WeakReference<Context>

    fun intentData(data: P? = null): Intent
    fun navigate(intent: Intent? = null)
}