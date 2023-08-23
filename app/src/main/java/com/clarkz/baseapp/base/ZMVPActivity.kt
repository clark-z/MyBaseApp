package com.clarkz.baseapp.base

import android.graphics.Color
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ZMVPActivity<T : ViewBinding> constructor(
    barTitleId: Int? = null,  //标题
    toolbarVisible: Boolean = true,  //标题栏是否可见
    toolbarBackgroundColor: Int = Color.WHITE,  //标题栏背景颜色
    toolbarBackgroundImage: Int? = null,   //标题栏背景图片
    toolbarBackBtnRes: Int? = null,  //标题栏返回按钮
    backgroundColor: Int = Color.WHITE,  //内容视图背景颜色
    backgroundImage: Int? = null  //内容视图背景图片
) : ZBaseActivity<T>(
    barTitleId,
    toolbarVisible,
    toolbarBackgroundColor,
    toolbarBackgroundImage,
    toolbarBackBtnRes,
    backgroundColor,
    backgroundImage
), IZBaseView {

    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onCreate(intent)
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