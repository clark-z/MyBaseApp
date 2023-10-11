package com.clarkz.baseapp.modules.ui

import com.clarkz.baseapp.R
import com.clarkz.baseapp.base.ZMVPActivity
import com.clarkz.baseapp.databinding.ActivityLoginBinding
import com.clarkz.baseapp.modules.mvp.contract.IUserContract
import com.clarkz.baseapp.modules.mvp.presenter.UserPresenter

class LoginActivity : ZMVPActivity<ActivityLoginBinding>(R.string.login_btn), IUserContract.IView {

    override val mPresenter: UserPresenter
        get() = UserPresenter(this)


//    override fun onCreate(savedInstanceState: Bundle?) {
//        with(window){
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//            val slide = Slide(Gravity.END)
//            slide.duration = 500
//            enterTransition = slide
//        }
//        super.onCreate(savedInstanceState)
//    }

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)
//    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
    }

    override fun initEvent() {
        addClickEvents()
    }

    override fun loginSuccess() {
    }

    override fun loginFail() {
    }

    private fun addClickEvents() {
        vb.btnLogin.setOnClickListener {
            mPresenter.loginByPassword("123456", "123456")
        }
    }


}