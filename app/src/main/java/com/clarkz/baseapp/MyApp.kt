package com.clarkz.baseapp

import android.app.Application
import android.content.Context
import com.clarkz.network.RetrofitManager
import com.clarkz.network.api.ApiService
import kotlin.properties.Delegates

class MyApp : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set

        var httpManager: RetrofitManager<ApiService> by Delegates.notNull()
            private set
    }


    override fun onCreate() {
        super.onCreate()

        context = this

        initHttpManager()
    }

    private fun initHttpManager(){
        httpManager = RetrofitManager(this, ApiService::class.java,"http://www.topozhe.com/ws/tupozhe/", true)
    }
}