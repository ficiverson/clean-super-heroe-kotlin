package test.kotlin.clean.ficiverson.cleansh.navigator

import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.Serializable

/**
 * Created by f.souto.gonzalez on 12/09/2018.
 */
class IntentData {
    companion object {

        const val BASE_ROUTE_APP = "heroes://"

        fun <T : Serializable> createNavigation(data: T?, context: Context, vararg screens: String): Array<out Intent>? {
            val taskStackBuilder = TaskStackBuilder.create(context)

            screens.forEach {
                val uriRoute = Uri.parse("$BASE_ROUTE_APP$it").buildUpon()
                taskStackBuilder.addNextIntent(
                    Intent().apply {
                        setData(uriRoute.build())
                        if (data !is NoParams) {
                            putExtra("data", data)
                        }
                    })
            }
            return taskStackBuilder.intents
        }
    }
}