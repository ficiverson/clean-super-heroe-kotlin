package test.kotlin.clean.ficiverson.cleansh.login

import android.content.Context
import android.content.Intent
import test.kotlin.clean.ficiverson.cleansh.BaseRouter
import test.kotlin.clean.ficiverson.cleansh.heroelist.SuperHeroesActivity
import java.lang.ref.WeakReference


/**
 * Created by f.souto.gonzalez on 10/09/2018.
 */
class MainRouter(override val context: WeakReference<Context>) : BaseRouter<Unit?> {

    override fun intentData(data: Unit?): Intent {
        //TODO take into account the params to create the
        return Intent(context.get(), SuperHeroesActivity::class.java)
    }

    override fun navigate(intent: Intent?) {
        context.get()?.startActivity(intent)
    }
}