package com.example.polidea.base.view

import androidx.appcompat.app.AppCompatActivity
import com.example.polidea.base.domain.presenter.BasePresenter
import com.example.polidea.base.domain.viewing.BaseViewing
import timber.log.Timber

abstract class BaseActivity<V : BaseViewing, P : BasePresenter<V>> : AppCompatActivity() {

    //region lifecycle

    override fun onResume() {
        super.onResume()
        bindPresenter()
    }

    override fun onPause() {
        unbindPresenter()
        super.onPause()
    }

    //endregion lifecycle

    //region presenter

    abstract fun providePresenter(): P

    private fun bindPresenter() {
        Timber.d("bindPresenter")
        (this as? V)?.let(providePresenter()::bind)
    }

    private fun unbindPresenter() {
        Timber.d("unbindPresenter")
        providePresenter().unbind()
    }

    //endregion presenter
}