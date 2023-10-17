package com.clarkz.baseapp.modules.mvp.presenter

import com.clarkz.baseapp.base.BaseServerRsp
import com.clarkz.baseapp.base.ZBasePresenter
import com.clarkz.baseapp.modules.mvp.contract.IUserContract
import com.clarkz.baseapp.modules.mvp.model.UserModel
import com.clarkz.network.MySubscriber

class UserPresenter constructor(private val view: IUserContract.IView) : IUserContract.IPresenter,
    ZBasePresenter<IUserContract.IView>(view) {

    override fun loginByPassword(account: String, password: String, loadingText: String) {
        UserModel.loginByPassword(account, password)
            .subscribe(object : MySubscriber<BaseServerRsp<String>>(view, true, loadingText) {
                override fun onNext(t: BaseServerRsp<String>) {
                    mView.get()?.loginSuccess()
                }
            })
    }


}