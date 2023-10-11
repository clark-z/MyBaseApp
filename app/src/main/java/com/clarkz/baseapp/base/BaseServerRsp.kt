package com.clarkz.baseapp.base

/**
 * 服务器响应体
 */
data class BaseServerRsp<T>(val code: Int, val msg: String, val data: T?)