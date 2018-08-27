package test.kotlin.clean.ficiverson.presentation.heroeslist

import org.buffer.android.boilerplate.domain.model.SuperHeroe
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import java.lang.ref.WeakReference

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */
class SuperHeroeListPresenter(
    view: SuperHeroeListViewTranslator,
    private val getSuperHeroes: GetSuperHeroesUseCase,
    private val invoker: UseCaseInvoker = UseCaseInvoker(),
    private val superHeroeMapper: SuperHeroeMapper = SuperHeroeMapper()
) : BasePresenter<SuperHeroeListViewTranslator>(WeakReference(view)) {

    override fun onCreate() {
        super.onCreate()
        val params = GetHeroeParams(1)
        invoker.execute(getSuperHeroes, params, NetworkAndStorage, ::retrieveHeroes)
    }

    override fun onStop() {
        super.onStop()
        invoker.cancelAllAsync()
    }

    private fun retrieveHeroes(result: Result<List<SuperHeroe>>) {
        when (result) {
            is Success -> view()?.showData(result.data.map { superHeroeMapper.mapToView(it) })
            is Error -> view()?.showErrorState()
        }
    }
}

interface SuperHeroeListViewTranslator {
    fun showData(data: List<SuperHeroeView>)
    fun showErrorState()
}
