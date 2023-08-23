package com.clarkz.baseapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ZBaseFragment<T: ViewBinding>: Fragment() {

    open val myActivity : ZBaseActivity<*> by lazy {
        requireActivity() as ZBaseActivity<*>
    }

    //内容视图ViewBinding
    open lateinit var vb: T

    /**
     * 获取内容视图
     */
    abstract fun getViewBinding(): T

    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 初始化事件
     */
    abstract fun initEvent()

    /**
     * 初始化数据
     */
    open fun initData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = getViewBinding()
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        initView()

        initEvent()
    }
}