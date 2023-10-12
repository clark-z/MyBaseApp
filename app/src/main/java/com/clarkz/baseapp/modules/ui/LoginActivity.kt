package com.clarkz.baseapp.modules.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Handler
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnRepeat
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
//            mPresenter.loginByPassword("123456", "123456")

//            val scaleAnimator = ObjectAnimator.ofFloat(vb.btnLogin, "scaleX", 1f, 2f, 1f)
//            scaleAnimator.duration = 1000
//            scaleAnimator.start()
//
//            val scaleAnimator2 = ObjectAnimator.ofFloat(vb.btnLogin, "scaleY", 1f, 2f, 1f)
//            scaleAnimator2.duration = 1000
//            scaleAnimator2.start()

            val scale = ObjectAnimator.ofFloat(vb.viewRound, "radius", 100f, 300f)
//            scale.duration = 2000
            scale.repeatCount = ValueAnimator.INFINITE
            scale.repeatMode = ValueAnimator.RESTART
//            scale.interpolator = LinearInterpolator()
//            scale.start()

            val alpha = ObjectAnimator.ofFloat(vb.viewRound, "alpha", 1f, 0f)
//            alpha.duration = 2000
            alpha.repeatCount = ValueAnimator.INFINITE
            alpha.repeatMode = ValueAnimator.RESTART
//            alpha.interpolator = LinearInterpolator()
//            alpha.start()

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scale, alpha)
            animatorSet.duration = 2000
            animatorSet.interpolator = LinearInterpolator()

            animatorSet.start()
        }
    }


}