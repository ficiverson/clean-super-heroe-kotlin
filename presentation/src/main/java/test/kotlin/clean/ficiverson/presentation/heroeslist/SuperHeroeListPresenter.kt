package test.kotlin.clean.ficiverson.presentation.heroeslist

import org.buffer.android.boilerplate.domain.interactor.browse.GetSuperHeroesUseCase
import org.buffer.android.boilerplate.domain.model.SuperHeroe
import org.buffer.android.boilerplate.domain.repository.SuperHeroesRepositoryContract
import test.kotlin.clean.ficiverson.executor.*
import test.kotlin.clean.ficiverson.presentation.BasePresenter
import test.kotlin.clean.ficiverson.presentation.mapper.SuperHeroeMapper

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */
class SuperHeroeListPresenter(private val superHeroesView: SuperHeroeListViewTranslator) : BasePresenter {

//    val getSuperHeroes: UseCase<GetSuperHeroesUseCase.Params, List<SuperHeroe>>,
//    val superHeroeMapper: SuperHeroeMapper,
//    val invoker: Invoker) : BasePresenter {

    private val getSuperHeroes: UseCase<GetHeroeParams, List<SuperHeroe>> = GetSuperHeroesUseCase(object : SuperHeroesRepositoryContract {
        override fun getSuperHeroes(params: Params, policy: CachePolicy): Result<List<SuperHeroe>> =
            Success(mutableListOf<SuperHeroe>())
    })
    private val superHeroeMapper = SuperHeroeMapper()
    private val invoker = UseCaseInvoker()


    init {
        superHeroesView.setPresenter(this)

//        1.sumaUno(2)
//
//        1 sumaUno 2
//
//        addText {
//            this + "hi"
//            this + " ho"
//            addText { toUpperCase() }
//        }

    }

    override fun start() {
        val params = GetHeroeParams(page = 1)
        invoker.execute(getSuperHeroes, params, NetworkAndStorage, ::retrieveHeroes)
    }

    override fun stop() {
        invoker.cancelAllAsync()
    }

    fun retrieveHeroes(result: Result<List<SuperHeroe>>) {
        when (result) {
            is Success -> superHeroesView.showData(result.data.map { superHeroeMapper.mapToView(it) })
            is Error -> superHeroesView.showErrorState()
        }
    }

//    infix fun Int.sumaUno(x: Int): Int { return x}
//
//    fun addText(func: String.()->Unit->String.()) = String().apply(func)
}
