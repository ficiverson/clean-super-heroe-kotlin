package test.kotlin.clean.ficiverson.cleansh.jump

import android.os.Bundle
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.cleansh.login.MainRouter
import test.kotlin.clean.ficiverson.cleansh.navigator.AppLink
import test.kotlin.clean.ficiverson.presentation.jump.JumpPresenter
import test.kotlin.clean.ficiverson.presentation.jump.JumpViewTranslator
import kotlin.clean.ficiverson.cleansh.R

/**
 * Created by f.souto.gonzalez on 12/09/2018.
 */
@AppLink(MainRouter.JUMP)
class JumpActivity : BaseActivity<JumpPresenter>(), JumpViewTranslator {

    override val presenter: JumpPresenter by injectActivity()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jump)

    }

}