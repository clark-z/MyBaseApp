package com.clarkz.baseapp.modules.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.animation.doOnEnd
import com.clarkz.baseapp.R
import com.clarkz.baseapp.base.ZBaseActivity
import com.clarkz.baseapp.databinding.ActivityMainBinding

class MainActivity : ZBaseActivity<ActivityMainBinding>(barTitleId = R.string.app_name) {

//    override fun getLayoutId(): Int = R.layout.activity_main

    private var isSplashView = false

    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        with(window){
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
////            val slide = Slide(Gravity.START)
////            slide.duration = 500
//            exitTransition = slide
//        }
//        super.onCreate(savedInstanceState)
//    }

    override fun initView() {
        //挂起启动界面
//        val contentView = findViewById<View>(android.R.id.content)
//        contentView.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
//            override fun onPreDraw(): Boolean {
//                return false  //true
//            }
//
//        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val path = Path()
                path.moveTo(1.0f, 1.0f)
                path.lineTo(0f, 0f)
                val scaleOut =
                    ObjectAnimator.ofFloat(splashScreenView, View.SCALE_X, View.SCALE_Y, path)
                scaleOut.duration = 1000
                scaleOut.doOnEnd {
                    splashScreenView.remove()

                    isSplashView = true
                    handler.postDelayed({
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 2000)
                }
                scaleOut.start()
            }
        }

        if (!isSplashView) {
            handler.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }

    override fun initEvent() {
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}