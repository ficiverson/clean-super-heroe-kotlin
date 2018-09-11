package test.kotlin.clean.ficiverson.cleansh.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.presentation.MainPresenter
import test.kotlin.clean.ficiverson.presentation.MainViewTranslator
import java.lang.ref.WeakReference
import kotlin.clean.ficiverson.cleansh.R

class MainActivity : BaseActivity<MainPresenter>(), MainViewTranslator {

    override val presenter: MainPresenter by injectActivity()
    lateinit var router: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router = MainRouter(WeakReference(this.applicationContext))
        loginSubmitButton.setOnClickListener { presenter.onSubmitButtonClick() }
    }

    override fun openSuperHeroesScreen() {
        with(router) {
            navigate(intentData())
        }
    }
}