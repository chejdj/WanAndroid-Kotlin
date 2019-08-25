package com.chejdj.wanandroid_kotlin.ui.base

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.TextView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonTagsViewPagerAdapter

/**
 * TagLayout+ViewPager的模版类
 */
abstract class BaseTagFragment : BaseFragment() {
    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout
    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager
    @BindView(R.id.title)
    lateinit var title: TextView
    lateinit var viewPagerAdapter: CommonTagsViewPagerAdapter
    var type: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_tag
    }

    fun showTags(tagsList: List<String>, cidNumbers: List<Int>) {
        for (item in tagsList) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
        }
        viewPagerAdapter = CommonTagsViewPagerAdapter(tagsList, cidNumbers, type, childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 0
        tabLayout.setupWithViewPager(viewPager)
        viewPagerAdapter.notifyDataSetChanged()
    }
}