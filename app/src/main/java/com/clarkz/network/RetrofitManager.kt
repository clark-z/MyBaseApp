package com.clarkz.network

import android.content.Context
import com.clarkz.network.converter.CustomGsonConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitManager<T> constructor(
    context: Context,
    service: Class<T>,
    private val baseUrl: String,
    private val isDebug: Boolean
) {
    var apiService: T

    //设置 请求的缓存的大小跟位置
    private var cache: Cache? = null//Cache(cacheFile, 52428800L) //50Mb 缓存的大小

    init {
        apiService = getRetrofit().create(service)
        val cacheFile = File(context.getExternalFilesDir(""), "cache")
        cache = Cache(cacheFile, 1024 * 1024 * 50)
    }


    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(CustomGsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level =
            if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .cache(cache)  //添加缓存
//            .addInterceptor(addCacheInterceptor())
//            .addInterceptor(addQueryParameterInterceptor())  //参数添加
//            .addInterceptor(addHeaderInterceptor()) // token过滤
            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//            .addInterceptor(AddCookiesInterceptor(SmartPetApp.context))
//            .addInterceptor(SaveCookiesInterceptor(SmartPetApp.context))
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
    }
}