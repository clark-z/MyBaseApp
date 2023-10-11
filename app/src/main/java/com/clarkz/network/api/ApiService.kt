package com.clarkz.network.api

import com.clarkz.baseapp.base.BaseServerRsp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * 密码登录
     */
    @GET("user/loginPassword")
    fun loginWithPassword(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("mobileId") mobileId: String
    ): Observable<BaseServerRsp<String>>

}