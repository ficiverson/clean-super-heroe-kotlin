package test.kotlin.clean.ficiverson.cleansh.heroelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_super_heroes.*
import test.kotlin.clean.ficiverson.cleansh.BaseActivity
import test.kotlin.clean.ficiverson.cleansh.injection.injectActivity
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesPresenter
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesViewTranslator
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesActivity : BaseActivity<SuperHeroesPresenter>(), SuperHeroesViewTranslator {

    override val presenter: SuperHeroesPresenter by injectActivity()

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SuperHeroesActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_heroes)
    }

    override fun showData(data: List<SuperHeroeView>) {
        superHeroesTitle.text = "The size is: ${data.size}"
    }

    override fun showErrorState() {
        superHeroesTitle.text = "Error"
    }
}