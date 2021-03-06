package test.kotlin.clean.ficiverson.presentation.heroelist

import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.model.SuperHeroe
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import java.lang.ref.WeakReference

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */
class SuperHeroesPresenter(
    view: SuperHeroesViewTranslator,
    private val getSuperHeroes: GetSuperHeroesUseCase,
    private val invoker: UseCaseInvoker = UseCaseInvoker(),
    private val superHeroeMapper: SuperHeroeMapper = SuperHeroeMapper()
) : BasePresenter<SuperHeroesViewTranslator>(WeakReference(view)) {

    override fun onCreate() {
        super.onCreate()
        val params = GetHeroeParams(1)
        invoker.execute(UseCaseExecutor(getSuperHeroes, params)) {
            retrieveHeroes(it)
        }
    }

    override fun onStop() {
        invoker.cancelAllAsync()
        super.onStop()
    }

    fun onSuperHeroeClick(superHeroe: SuperHeroeView) {
        view()?.showSuperHeroeName(superHeroe.name)
    }

    private fun retrieveHeroes(result: Result<List<SuperHeroe>>) {
        when (result) {
            is Success -> view()?.showData(result.data.map { superHeroeMapper.mapToView(it) })
            is Error, is NoInternetError -> view()?.showErrorState()
        }
    }
}

interface SuperHeroesViewTranslator {
    fun showSuperHeroeName(superHeroeName: String)
    fun showData(data: List<SuperHeroeView>)
    fun showErrorState()
}
