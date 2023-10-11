package com.clarkz.baseapp.modules.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.clarkz.baseapp.R
import com.clarkz.baseapp.base.ZBaseActivity
import com.clarkz.baseapp.databinding.ActivityMainBinding

class MainActivity : ZBaseActivity<ActivityMainBinding>(R.string.app_name) {

//    override fun getLayoutId(): Int = R.layout.activity_main

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


    }

    override fun initEvent() {
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
//            finish()
        }, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}