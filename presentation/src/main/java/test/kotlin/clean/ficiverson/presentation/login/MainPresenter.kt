package test.kotlin.clean.ficiverson.presentation.login

import test.kotlin.clean.ficiverson.presentation.BasePresenter
import java.lang.ref.WeakReference


class MainPresenter(
    view: MainViewTranslator
) : BasePresenter<MainViewTranslator>(WeakReference(view)) {

    fun onSubmitButtonClick() {
        view()?.openSuperHeroesScreen()
    }

    fun onJumpButtonClick() {
        view()?.openJumpScreen()
    }
}


interface MainViewTranslator {
    fun openSuperHeroesScreen()
    fun openJumpScreen()
}
