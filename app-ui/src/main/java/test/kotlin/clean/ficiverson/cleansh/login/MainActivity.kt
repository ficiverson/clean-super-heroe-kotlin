package test.kotlin.clean.ficiverson.cleansh.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.cleansh.navigator.AppLink
import test.kotlin.clean.ficiverson.presentation.login.MainPresenter
import test.kotlin.clean.ficiverson.presentation.login.MainViewTranslator
import kotlin.clean.ficiverson.cleansh.R

@AppLink(MainRouter.LOGIN)
class MainActivity : BaseActivity<MainPresenter>(), MainViewTranslator {

    override val presenter: MainPresenter by injectActivity()
    private val router: MainRouter by injectActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginSubmitButton.setOnClickListener { presenter.onSubmitButtonClick() }
        loginJumButton.setOnClickListener { presenter.onJumpButtonClick() }
    }

    override fun openSuperHeroesScreen() {
        router.goToScreen(MainRouter.HEROE_LIST)
    }

    override fun openJumpScreen() {
        router.goToScreen(MainRouter.HEROE_LIST, MainRouter.JUMP)
    }

}