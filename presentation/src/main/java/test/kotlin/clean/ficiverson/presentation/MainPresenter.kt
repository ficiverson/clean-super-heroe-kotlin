package test.kotlin.clean.ficiverson.presentation

import java.lang.ref.WeakReference


class MainPresenter(
    view: MainViewTranslator
) : BasePresenter<MainViewTranslator>(WeakReference(view)) {

    fun onSubmitButtonClick() {
        view()?.openSuperHeroesScreen()
    }
}

interface MainViewTranslator {
    fun openSuperHeroesScreen()
}
