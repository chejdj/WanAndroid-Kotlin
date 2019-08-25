package com.chejdj.wanandroid_kotlin.ui.commons.adapter

import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleFragment

/**
 * ViewPager的公用Adapter
 */
class CommonTagsViewPagerAdapter(
    subTitles: List<String>,
    cidNumbers: List<Int>,
    type: Int,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val subTitles = subTitles
    private val cidNumbers = cidNumbers
    private val type = type

    override fun getItem(p0: Int): Fragment {
        return CommonArticleFragment.getCommonArticleFragment(cidNumbers[p0], type)
    }

    override fun getCount(): Int {
        return subTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return subTitles[position]
    }

    override fun saveState(): Parcelable? {
        return null
    }
}