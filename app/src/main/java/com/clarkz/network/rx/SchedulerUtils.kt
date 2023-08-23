package com.clarkz.network.rx

object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}