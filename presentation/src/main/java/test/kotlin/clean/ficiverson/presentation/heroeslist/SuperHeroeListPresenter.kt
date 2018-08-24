package test.kotlin.clean.ficiverson.presentation.heroeslist

import org.buffer.android.boilerplate.domain.interactor.browse.GetSuperHeroesUseCase
import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.buffer.android.boilerplate.domain.repository.SuperHeroesRepositoryContract
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import java.lang.ref.WeakReference

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */
class SuperHeroeListPresenter(
    view: SuperHeroeListViewTranslator
) : BasePresenter<SuperHeroeListViewTranslator>(WeakReference(view)) {

//    val getSuperHeroes: UseCase<GetSuperHeroesUseCase.Params, List<SuperHeroe>>,
//    val superHeroeMapper: SuperHeroeMapper,
//    val invoker: Invoker) : BasePresenter {

    private val getSuperHeroes: UseCase<GetHeroeParams, List<SuperHeroe>> = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: Params, policy: CachePolicy): Result<List<SuperHeroe>> =
            Success(emptyList())
    })
    private val superHeroeMapper = SuperHeroeMapper()
    private val invoker = UseCaseInvoker()

    init {
        superHeroesView.setPresenter(this)
    }

    override fun start() {
        val params = GetHeroeParams(page = 1)
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
