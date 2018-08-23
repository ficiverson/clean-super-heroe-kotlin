package test.kotlin.clean.ficiverson.cleansh

import android.os.Bundle
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListPresenter
import test.kotlin.clean.ficiverson.presentation.heroeslist.SuperHeroeListViewTranslator
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView

class SuperHeroesActivity : BaseActivity(), SuperHeroeListViewTranslator {


    val presenter: SuperHeroeListPresenter by lazy { SuperHeroeListPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        presenter.start()
        //inject deps  this.presenter = SuperHeroeListPresenter(this)
    }

    override fun showData(data: List<SuperHeroeView>) {
        //inflate the view
    }

    override fun setPresenter(presenter: BasePresenter) {
        //show error
    }
}