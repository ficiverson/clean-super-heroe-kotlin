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

    override fun onCreate() {}

    override fun onReady() {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onDestroy() {}

    override fun onRestoreInstanceState() {}

    override fun onSaveInstanceState() {}

    override fun onActivityResult() {}
}
