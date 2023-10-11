package com.clarkz.network

import com.clarkz.baseapp.base.IZBaseView
import com.clarkz.network.exception.ApiException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.lang.ref.WeakReference

abstract class MySubscriber<T> constructor(
    view: IZBaseView,
    private val isShowLoading: Boolean = true
) : Observer<T> {
    private val mView: WeakReference<IZBaseView> by lazy { WeakReference(view) }

    override fun onSubscribe(d: Disposable) {
        mView.get()?.apply {
            if (isShowLoading) {
                showLoading()
            }
            addSubscribe(d)
        }
    }

    override fun onComplete() {
        mView.get()?.apply {
            if (isShowLoading){
                hideLoading()
            }
        }
    }

    override fun onError(e: Throwable) {
        mView.get()?.apply {
            if (isShowLoading){
                hideLoading()
            }

            if (e is ApiException){
                //服务器响应码异常处理
                handleApiError(e)
            }else{
                //其它异常
                handleOtherError(e)
            }
        }
    }

    private fun handleApiError(e: ApiException){

    }

    private fun handleOtherError(e: Throwable){
         when (e){
             is HttpException -> {

             }
         }
    }
}