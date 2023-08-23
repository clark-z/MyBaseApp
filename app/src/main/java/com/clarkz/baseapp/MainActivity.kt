package com.clarkz.baseapp

import com.clarkz.baseapp.base.ZBaseActivity
import com.clarkz.baseapp.databinding.ActivityMainBinding

class MainActivity : ZBaseActivity<ActivityMainBinding>(R.string.app_name) {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initEvent() {
    }

}