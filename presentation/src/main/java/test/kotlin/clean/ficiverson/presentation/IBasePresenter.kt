package test.kotlin.clean.ficiverson.presentation;

/**
 * Implementation of a presenter based on the MVP pattern.
 */
interface IBasePresenter {

    /**
     * On of callback for the activity or fragment lifecycle.
     */
    fun onCreate()

    /**
     * Callback that ensures the global state of the application is ready. This ensures that if there
     * is something asynchronous to be loaded it is done in the proper time.
     */
    fun onReady()

    /**
     * On start equivalent callback for an activity or a fragment.
     */
    fun onStart()

    /**
     * On pause equivalent callback for an activity or a fragment.
     */
    fun onPause()

    /**
     * On restore lifecycle callback for an activity or a fragment.
     */
    fun onResume()

    /**
     * On stop equivalent callback for an activity or a fragment.
     */
    fun onStop()

    /**
     * On destroy lifecycle callback for an activity or a fragment.
     */
    fun onDestroy()

    /**
     * On restore the fragment or activity state.
     */
    fun onRestoreInstanceState()

    /**
     * On saveBitmap the fragment or activity state.
     */
    fun onSaveInstanceState()

    /**
     * Allows the startBroadcastWith activity for result.
     */
    fun onActivityResult()
}
