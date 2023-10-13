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

//    /**
//     * 是否需要token
//     */
//    private fun isNeedToken(encodedPath: String): Boolean {
//
//        noTokenApi.forEach {
//            if (encodedPath.contains(it)) {
//                return false
//            }
//        }
//        return true
//    }

//    /**
//     * 设置公共参数, token
//     */
//    private fun addQueryParameterInterceptor(): Interceptor {
//
//        return Interceptor { chain ->
//            val originalRequest = chain.request()
//            val encodedPath = originalRequest.url().encodedPath()
//
//            val sp = MyApp.context.getSharedPreferences(
//                Constants.SHARE_BREAKTHROUGH,
//                Context.MODE_PRIVATE
//            )
//            val token = sp.getString(Constants.SESSION_TOKEN, "")
//
//            if (isNeedToken(encodedPath) && !token.isNullOrEmpty()) {  //排除不需要token的请求，并且token不为空
//                val modifiedUrl = originalRequest.url()
//                    .newBuilder()
//                    .addQueryParameter("token", token)
//                    .build()
//                val request = originalRequest.newBuilder().url(modifiedUrl).build()
//                chain.proceed(request)
//            } else {
//                chain.proceed(originalRequest)
//            }
//        }
//    }

//    /**
//     * 设置头
//     */
//    private fun addHeaderInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val originalRequest = chain.request()
//            val requestBuilder = originalRequest.newBuilder()
//                // Provide your custom header here
////                .header("token", "")
//                .method(originalRequest.method, originalRequest.body)
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }
//    }

//    private fun addCacheInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            var request = chain.request()
//
//            if (NetworkUtils.isAvailable() || request.url
//                    .queryParameter("token") == null
//            ) { //没有token的请求不读取缓存
//                val response = chain.proceed(request)
//                //有网络不适用缓存
//                val maxAge = 10  //10s内的刷新获取缓存
//                response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    .header("Cache-Control", "public, max-age=$maxAge")
//                    .build()
//            } else {
//                //无网络，使用缓存
//                request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build()
//                val response = chain.proceed(request)
//                val maxStale = 60 * 60 * 24 * 3   //缓存3天，实际无效果，源码会一直缓存（待验证）
//                response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
//                    .build()
//            }
//        }
//    }
}