package com.clarkz.baseapp

import android.app.Application
import android.content.Context
import com.clarkz.network.RetrofitManager
import com.clarkz.network.api.ApiService
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
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

        initLogger()
    }

    private fun initHttpManager() {
        httpManager = RetrofitManager(
            this,
            ApiService::class.java,
            "http://www.topozhe.com/ws/tupozhe/",
            BuildConfig.DEBUG
        )
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
//            .methodCount(2)  // (Optional) How many method line to show. Default 2
//            .methodOffset(5) // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("MAX_LOGGER") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()


        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

//        val csvFormatStrategy = CsvFormatStrategy.newBuilder()
//            .tag("CHEER_LOGGER")
//            .build()
//        Logger.addLogAdapter(DiskLogAdapter(csvFormatStrategy))
    }
}