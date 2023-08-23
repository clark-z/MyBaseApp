package com.clarkz.baseapp.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ZMVPFragment<T: ViewBinding>: ZBaseFragment<T>(), IZBaseView {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter.onCreateView(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    override fun unSubscribe() {
        compositeDisposable?.apply {
            dispose()
            clear()
        }
        compositeDisposable = null
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}