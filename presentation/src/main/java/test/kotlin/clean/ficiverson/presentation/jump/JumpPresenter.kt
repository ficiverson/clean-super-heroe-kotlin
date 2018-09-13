package test.kotlin.clean.ficiverson.presentation.jump

import test.kotlin.clean.ficiverson.presentation.BasePresenter
import java.lang.ref.WeakReference


class JumpPresenter(
    view: JumpViewTranslator
) : BasePresenter<JumpViewTranslator>(WeakReference(view)) {

}


interface JumpViewTranslator {

}
