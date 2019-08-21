package com.chejdj.wanandroid_kotlin.ui.base

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R

/**
 * TagLayout+ViewPager的模版类
 */
abstract class BaseTagFragment : BaseFragment() {
    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout
    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    override fun getLayoutId(): Int {
        return R.layout.fragment_tag
    }

    override fun initView() {

    }

    fun showTags(tagsList: List<String>, cidNumbers: List<Int>) {
        for (item in tagsList) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
        }
    }

}