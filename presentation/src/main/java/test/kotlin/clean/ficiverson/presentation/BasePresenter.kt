package test.kotlin.clean.ficiverson.presentation

import java.lang.ref.WeakReference

/**
 * Base class for the presenter.
 */
abstract class BasePresenter<out T>
/**
 * The base constructor with the view translator.

 * @param view The view translator of the MVP.
 */
    (private val view: WeakReference<T>) : IBasePresenter {

    /**
     * Provides the view.

     * @return The current view.
     */
    fun view(): T? = view.get()

    override fun onCreate() = Unit

    override fun onReady() = Unit

    override fun onStart() = Unit

    override fun onResume() = Unit

    override fun onPause() = Unit

    override fun onStop() = Unit

    override fun onDestroy() = Unit

    override fun onRestoreInstanceState() = Unit

    override fun onSaveInstanceState() = Unit

    override fun onActivityResult() = Unit
}
