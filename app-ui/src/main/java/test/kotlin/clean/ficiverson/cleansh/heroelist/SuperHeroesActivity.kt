package test.kotlin.clean.ficiverson.cleansh.heroelist

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_super_heroes.*
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import test.kotlin.clean.ficiverson.cleansh.extensions.showToast
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesPresenter
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesViewTranslator
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R


class SuperHeroesActivity : BaseActivity<SuperHeroesPresenter>(), SuperHeroesViewTranslator {

    override val presenter: SuperHeroesPresenter by injectActivity()

    private val superHeroesAdapter = SuperHeroesAdapter(this, presenter::onSuperHeroeClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_heroes)

        superHeroesRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@SuperHeroesActivity, LinearLayoutManager.VERTICAL))
            adapter = superHeroesAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu.findItem(R.id.action_search)

        (searchMenuItem?.actionView as SearchView).apply {
            setSearchableInfo(manager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    superHeroesAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    superHeroesAdapter.filter.filter(query)
                    return false
                }
            })
        }

        return true
    }

    override fun showSuperHeroeName(superHeroeName: String) {
        showToast(superHeroeName)
    }

    override fun showData(data: List<SuperHeroeView>) {
        superHeroesAdapter.setSuperHeroes(data)
    }

    override fun showErrorState() {
//        superHeroesTitle.text = "Error"
    }
}