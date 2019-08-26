package com.chejdj.wanandroid_kotlin.ui.search

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * Created by zhuyangyang on 2019-08-26
 */
class SearchActivity : BaseActivity() {
    @BindView(R.id.search)
    lateinit var searchView: SearchView
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.flowLayout)
    lateinit var flowLayout: TagFlowLayout

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {

    }
}