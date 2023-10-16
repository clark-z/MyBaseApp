package com.clarkz.baseapp.base

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ZMVPActivity<T : ViewBinding> constructor(
    toolbarVisible: Boolean = true,  //标题栏是否可见
    @StringRes
    barTitleId: Int? = null,  //标题
    @ColorRes
    toolbarBackgroundColor: Int? = null,  //标题栏背景颜色
    @DrawableRes
    toolbarBackgroundImage: Int? = null,   //标题栏背景图片
    @DrawableRes
    toolbarBackBtnRes: Int? = null,  //标题栏返回按钮
    @ColorRes
    backgroundColor: Int? = null,  //内容视图背景颜色
    @DrawableRes
    backgroundImage: Int? = null  //内容视图背景图片
) : ZBaseActivity<T>(
    toolbarVisible,
    barTitleId,
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
        //没有下拉刷新的情况，显示自定义loading弹窗
        if (refreshLayout == null) {

        }
    }

    override fun hideLoading() {

        if (isRefresh) {
            finishRefresh()
        } else {
            finishLoadMore()
        }
    }

    override fun showShortToast(msg: String) {
        showToast(msg, Toast.LENGTH_SHORT)
    }
}