package com.clarkz.baseapp.base

import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.clarkz.baseapp.databinding.ActivityZbaseBinding
import com.orhanobut.logger.Logger

/**
 * @author: Clark Zhong
 * @date: 2023/08/22
 * @email: 290287498@qq.com
 * @description: 基础Activity
 */
abstract class ZBaseActivity<T : ViewBinding> constructor(
    private val barTitleId: Int? = null,  //标题
    private val toolbarVisible: Boolean = true,  //标题栏是否可见
    private val toolbarBackgroundColor: Int = Color.WHITE,  //标题栏背景颜色
    private val toolbarBackgroundImage: Int? = null,   //标题栏背景图片
    private val toolbarBackBtnRes: Int? = null,  //标题栏返回按钮
    private val backgroundColor: Int = Color.WHITE,  //内容视图背景颜色
    private val backgroundImage: Int? = null  //内容视图背景图片
) : AppCompatActivity() {

    /**
     * 基础视图ViewBinding
     */
    private val baseVB: ActivityZbaseBinding by lazy {
        ActivityZbaseBinding.inflate(layoutInflater)
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
        Logger.d("initData>>>>>>>")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initContentView()

        setContentView(baseVB.root)

        initData()
        initView()
        initEvent()
    }

    /**
     * 初始化标题栏
     */
    private fun initToolbar() {
        if (toolbarVisible) {
//            //设置支持action menu
//            setSupportActionBar(baseVB.vToolbar.root)

            //设置背景
            updateToolbarColor(toolbarBackgroundColor)
            toolbarBackgroundImage?.let {
                updateToolbarImage(it)
            }

            //设置标题
            barTitleId?.let {
                updateToolbarTitle(getString(it))
            }

            //设置返回按钮
            toolbarBackBtnRes?.let {
                baseVB.vToolbar.root.setNavigationIcon(it)
            }

            //设置返回按钮点击事件
            baseVB.vToolbar.root.setNavigationOnClickListener {
                onClickBackBtn()
            }
        } else {
            baseVB.root.removeView(baseVB.vToolbar.root)
        }
    }

    /**
     * 初始化内容视图
     */
    private fun initContentView() {
        updateBackgroundColor(backgroundColor)

        backgroundImage?.let {
            updateBackgroundImage(it)
        }

        vb = getViewBinding()

        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        baseVB.vContainer.addView(vb.root, lp)
    }

    /**
     * 返回按钮事件
     */
    open fun onClickBackBtn() {
        finish()
    }

    /**
     * 更新背景颜色
     */
    open fun updateBackgroundColor(color: Int) {
        baseVB.root.setBackgroundColor(color)
    }

    /**
     * 更新背景图片
     */
    open fun updateBackgroundImage(res: Int) {
        baseVB.root.setBackgroundResource(res)
    }

    /**
     * 更新标题
     */
    open fun updateToolbarTitle(title: String) {
        baseVB.vToolbar.tvBarTitle.text = title
    }

    /**
     * 更新标题栏背景颜色
     */
    open fun updateToolbarColor(color: Int) {
        baseVB.vToolbar.root.setBackgroundColor(toolbarBackgroundColor)
    }

    /**
     * 更新标题栏背景图片
     */
    open fun updateToolbarImage(res: Int) {
        baseVB.vToolbar.root.setBackgroundResource(res)
    }

}