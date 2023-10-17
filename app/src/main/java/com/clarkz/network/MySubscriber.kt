package com.clarkz.network

import com.blankj.utilcode.util.NetworkUtils
import com.clarkz.baseapp.base.IZBaseView
import com.clarkz.network.exception.ApiException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.lang.ref.WeakReference
import java.net.ConnectException
import java.net.UnknownHostException

abstract class MySubscriber<T> constructor(
    view: IZBaseView,
    private val isShowLoading: Boolean = true,
    private val loadingText: String? = null

) : Observer<T> {
    private val mView: WeakReference<IZBaseView> by lazy { WeakReference(view) }

    override fun onSubscribe(d: Disposable) {
        mView.get()?.apply {

            if (!isNetworkAvailable()) {
                //网络不可用
                d.dispose()
                showNetworkDisconnected()
            } else {
                if (isShowLoading) {
                    showLoading(loadingText)
                }
                addSubscribe(d)
            }
        }
    }

    override fun onComplete() {
        mView.get()?.apply {
            if (isShowLoading) {
                hideLoading()
            }
        }
    }

    override fun onError(e: Throwable) {
        mView.get()?.apply {
            if (isShowLoading) {
                hideLoading()
            }

            if (e is ApiException) {
                //服务器响应码异常处理
                handleApiError(e)
            } else {
                //其它异常
                handleOtherError(e)
            }
        }
    }

    /**
     * 网络是否连接
     */
    private fun isNetworkAvailable(): Boolean {
        return NetworkUtils.isConnected()
    }

    /**
     * api请求异常
     */
    private fun handleApiError(e: ApiException) {
        mView.get()?.showShortToast(e.message ?: "未知错误")
    }

    /**
     * 其它网络异常
     */
    private fun handleOtherError(e: Throwable) {
        when (e) {
            is UnknownHostException -> {
                mView.get()?.showShortToast("网络异常!")
            }

            is JSONException -> {
                mView.get()?.showShortToast("数据解析异常!")
            }

            is ConnectException -> {
                mView.get()?.showShortToast("服务器连接异常!")
            }

            is HttpException -> {
                mView.get()?.showShortToast("Http响应异常!")
            }

            else -> {
                mView.get()?.showShortToast("未知错误!")
            }
        }
    }
}