package test.kotlin.clean.ficiverson.presentation;

import test.kotlin.clean.ficiverson.presentation.BasePresenter

interface BaseView<in P : BasePresenter> {

    fun showProgress()

    fun hideProgress()

    fun showEmptyData()

    fun showErrorState()

    fun hideErrorState()

    fun showEmptyState()

    fun hideEmptyState()

}