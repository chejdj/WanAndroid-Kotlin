package com.chejdj.wanandroid_kotlin.ui.commons.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleFragment

/**
 * ViewPager的公用Adapter
 */
class CommonTagsViewPagerAdapter(
    private val subTitles: List<String>,
    private val cidNumbers: List<Int>,
    private val type: Int,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {

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