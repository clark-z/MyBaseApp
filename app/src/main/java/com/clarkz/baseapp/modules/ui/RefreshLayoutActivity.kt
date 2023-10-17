package com.clarkz.baseapp.modules.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.clarkz.baseapp.R
import com.clarkz.baseapp.base.ZMVPActivity
import com.clarkz.baseapp.databinding.ActivityRefreshLayoutBinding
import com.clarkz.baseapp.modules.mvp.contract.IUserContract
import com.clarkz.baseapp.modules.mvp.presenter.UserPresenter

class RefreshLayoutActivity :
    ZMVPActivity<ActivityRefreshLayoutBinding>(barTitleId = R.string.refresh, toolbarBackgroundColor = R.color.white), IUserContract.IView {


    private val dataList = ArrayList<String>()
    private val adapter: BaseQuickAdapter<String, QuickViewHolder> by lazy {
        object : BaseQuickAdapter<String, QuickViewHolder>(dataList) {

            override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
                holder.setText(R.id.title_name, item)
            }

            override fun onCreateViewHolder(
                context: Context,
                parent: ViewGroup,
                viewType: Int
            ): QuickViewHolder {
                return QuickViewHolder(R.layout.item_refresh, parent)
            }
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    override val mPresenter: UserPresenter
        get() = UserPresenter(this)

    override fun getViewBinding(): ActivityRefreshLayoutBinding =
        ActivityRefreshLayoutBinding.inflate(layoutInflater)

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun initData() {
        for (i in 0..20) {
            dataList.add(i.toString())
        }
    }

    override fun initView() {

        val layoutManager = LinearLayoutManager(this)

        vb.recyclerView.layoutManager = layoutManager
        vb.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        vb.recyclerView.adapter = adapter
    }

    override fun initEvent() {
    }

    override fun loginSuccess() {
    }

    override fun loginFail() {
    }

    override fun refresh() {
        super.refresh()
        handler.postDelayed({
            finishRefresh()
            showToast("刷新成功!!")
        }, 2000)
    }

    override fun loadMore() {
        super.loadMore()
        handler.postDelayed({
            finishLoadMore()
            showToast("加载成功!!")
        }, 2000)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun finishRefresh() {
        super.finishRefresh()
        dataList.clear()
        initData()
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun finishLoadMore() {
        super.finishLoadMore()
        for (i in dataList.size until 20+dataList.size) {
            dataList.add(i.toString())
        }
        adapter.notifyDataSetChanged()
    }
}