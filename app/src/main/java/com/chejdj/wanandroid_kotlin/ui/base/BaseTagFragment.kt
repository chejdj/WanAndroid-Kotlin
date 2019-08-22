package com.chejdj.wanandroid_kotlin.ui.base

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.widget.TextView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleFragment
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
    private val fragments: ArrayList<Fragment> = ArrayList()
    lateinit var viewPagerAdapter: CommonTagsViewPagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_tag
    }

    override fun initView() {

    }

    fun showTags(tagsList: List<String>, cidNumbers: List<Int>) {
        for ((index, item) in tagsList.withIndex()) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
            fragments.add(CommonArticleFragment.getCommonArticleFragment(cidNumbers[index], 0))
        }
        viewPagerAdapter = CommonTagsViewPagerAdapter(cidNumbers, 0, childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}