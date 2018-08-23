package test.kotlin.clean.ficiverson.presentation.heroeslist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.presentation.BaseView
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */

interface SuperHeroeListViewTranslator : BaseView<BasePresenter> {

    fun showData(data: List<SuperHeroeView>)

    fun setPresenter(presenter: BasePresenter)
}