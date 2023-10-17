package com.clarkz.baseapp

import android.app.Application
import android.content.Context
import com.clarkz.network.RetrofitManager
import com.clarkz.network.api.ApiService
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import me.jessyan.autosize.AutoSizeConfig
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

        screenMatchConfig()

        initHttpManager()

        initLogger()
    }

    /**
     * 初始化网路请求
     */
    private fun initHttpManager() {
        httpManager = RetrofitManager(
            this,
            ApiService::class.java,
            "http://www.topozhe.com/ws/tupozhe/",
            BuildConfig.DEBUG
        )
    }

    /**
     * 初始化日志工具
     */
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

    /**
     * 初始化屏幕适配
     */
    private fun screenMatchConfig() {
        AutoSizeConfig.getInstance().apply {
//                    isUseDeviceSize = true
//            isBaseOnWidth = false    //默认以宽度适配

            setLog(false)
        }
    }
}