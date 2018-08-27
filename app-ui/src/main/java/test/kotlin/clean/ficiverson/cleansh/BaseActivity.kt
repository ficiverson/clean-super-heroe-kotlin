package test.kotlin.clean.ficiverson.cleansh

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import test.kotlin.clean.ficiverson.presentation.IBasePresenter

/*
* Base activity that supports MVP.
*/
abstract class BaseActivity<T : IBasePresenter> : AppCompatActivity() {

    /**
     * The current presenter.
     */
    abstract val presenter: T

    /**
     * Provides the presenter.

     * @return The presenter provided.
     */
    fun presenter(): T = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onReady()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.onRestoreInstanceState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult()
    }
}
