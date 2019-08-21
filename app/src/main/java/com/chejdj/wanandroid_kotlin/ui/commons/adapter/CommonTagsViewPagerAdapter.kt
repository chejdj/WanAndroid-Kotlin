package com.chejdj.wanandroid_kotlin.ui.commons.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleFragment

/**
 * ViewPager的公用Adapter
 */
class CommonTagsViewPagerAdapter(cidNumbers: List<Int>?, type: Int, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val cidNumbers: List<Int>? = cidNumbers
    private val fragmentType: Int = type

    override fun getItem(p0: Int): Fragment {
        return CommonArticleFragment.getCommonArticleFragment(cidNumbers?.get(p0) ?: 0, fragmentType)
    }

    override fun getCount(): Int {
        return cidNumbers?.size ?: 0
    }
}