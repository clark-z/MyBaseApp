package com.clarkz.baseapp.modules.mvp.model

import com.clarkz.baseapp.MyApp
import com.clarkz.baseapp.base.BaseServerRsp
import com.clarkz.network.rx.SchedulerUtils
import io.reactivex.Observable

object UserModel {


    fun loginByPassword(account: String, password: String): Observable<BaseServerRsp<String>> {

        return MyApp.httpManager.apiService.loginWithPassword(account, password, "123456").compose(SchedulerUtils.ioToMain())
    }

}