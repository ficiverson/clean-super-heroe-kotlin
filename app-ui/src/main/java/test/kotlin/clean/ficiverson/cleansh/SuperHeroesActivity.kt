package test.kotlin.clean.ficiverson.cleansh

import android.os.Bundle
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import test.kotlin.clean.ficiverson.presentation.AppModules
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListPresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListViewTranslator
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesActivity : BaseActivity<SuperHeroeListPresenter>(), KodeinAware, SuperHeroeListViewTranslator {

    override val kodein by lazy { AppModules().module }

    override val presenter: SuperHeroeListPresenter by kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showData(data: List<SuperHeroeView>) {
    }

    override fun showErrorState() {
    }
}