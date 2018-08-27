package test.kotlin.clean.ficiverson.cleansh.hereoelist

import android.os.Bundle
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import org.koin.android.ext.android.inject
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListPresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListViewTranslator
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesActivity : BaseActivity<SuperHeroeListPresenter>(), SuperHeroeListViewTranslator {

    override val presenter: SuperHeroeListPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showData(data: List<SuperHeroeView>) {
    }

    override fun showErrorState() {
    }
}