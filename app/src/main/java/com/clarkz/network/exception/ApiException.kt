package com.clarkz.network.exception

/**
 * 服务器接口异常
 */
class ApiException(val code: Int, private val msg: String?) : RuntimeException(msg)