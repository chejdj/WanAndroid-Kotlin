package com.chejdj.wanandroid_kotlin.base

import android.os.Bundle

abstract class BaseLazyLoadViewPagerFragment : BaseFragment() {
    private var isViewCreated: Boolean = false
    private var isDataLoad: Boolean = false
    var isVisibleToUser = false
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true
        tryLoadData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            tryLoadData()
        }
    }

    private fun isParentVisible(): Boolean {
        return parentFragment == null || (parentFragment?.isVisible == true)
    }

    private fun tryLoadData() {
        if (isViewCreated && isVisible && isParentVisible() && !isDataLoad) {
            loadData()
            dispatchChildLoadData()
            isDataLoad = true
        }
    }

    /**
     * ViewPager场景下，当前fragment可见，如果其子fragment也可见，则尝试让子fragment请求数据
     */
    private fun dispatchChildLoadData() {
        val fragments = childFragmentManager.fragments
        for (item in fragments) {
            if (item is BaseLazyLoadViewPagerFragment && item.isVisibleToUser) {
                item.tryLoadData()
            }
        }
    }

    protected abstract fun loadData()
}