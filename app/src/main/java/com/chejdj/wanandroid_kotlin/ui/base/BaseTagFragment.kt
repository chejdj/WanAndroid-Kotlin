package com.chejdj.wanandroid_kotlin.ui.base

import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonTagsViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * TagLayout+ViewPager的模版类
 */
abstract class BaseTagFragment : BaseLazyLoadFragment() {
    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    @BindView(R.id.title)
    lateinit var title: TextView
    private lateinit var viewPagerAdapter: CommonTagsViewPagerAdapter
    var type: Int = 0
    private val tagsData = ArrayList<String>()
    private val cidNumberData = ArrayList<Int>()


    override fun getLayoutId(): Int {
        return R.layout.fragment_tag
    }

    fun showTags(tagsList: List<PrimaryArticleDirectoryBean>) {
        tagsData.clear()
        cidNumberData.clear()
        for (item in tagsList) {
            tabLayout.addTab(tabLayout.newTab().setText(item.name))
            tagsData.add(item.name)
            cidNumberData.add(item.id)
        }
        viewPagerAdapter =
            CommonTagsViewPagerAdapter(tagsData, cidNumberData, type, childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.currentItem = 0
        viewPagerAdapter.notifyDataSetChanged()
    }

    override fun isDataEmpty(): Boolean {
        return tagsData.isEmpty() && cidNumberData.isEmpty()
    }
}