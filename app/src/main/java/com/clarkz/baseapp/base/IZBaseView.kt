package com.clarkz.baseapp.base

import io.reactivex.disposables.Disposable

interface IZBaseView {

    val mPresenter: ZBasePresenter<out IZBaseView>

    /**
     * 添加RxJava事件
     *
     * @param disposable Disposable
     */
    fun addSubscribe(disposable: Disposable)

    /**
     * 解除RxJava事件
     */
    fun unSubscribe()

    /**
     * 显示加载中
     */
    fun showLoading()

    /**
     * 隐藏加载中
     */
    fun hideLoading()

}