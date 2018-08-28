package test.kotlin.clean.ficiverson.cleansh

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import test.kotlin.clean.ficiverson.cleansh.heroelist.SuperHeroesActivity
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.presentation.MainPresenter
import test.kotlin.clean.ficiverson.presentation.MainViewTranslator
import kotlin.clean.ficiverson.cleansh.R

class MainActivity : BaseActivity<MainPresenter>(), MainViewTranslator {

    override val presenter: MainPresenter by injectActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginSubmitButton.setOnClickListener { presenter.onSubmitButtonClick() }
    }

    override fun openSuperHeroesScreen() {
        SuperHeroesActivity.startActivity(this)
    }
}