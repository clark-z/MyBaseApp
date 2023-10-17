package com.clarkz.baseapp.modules.mvp.contract

import com.clarkz.baseapp.base.IZBaseView

interface IUserContract {

    interface IView : IZBaseView {
      fun loginSuccess()

      fun loginFail()
    }

    interface IPresenter {
       fun loginByPassword(account: String, password: String, loadingText: String)
    }
}