package com.chejdj.wanandroid_kotlin.ui.base

abstract class BaseLazyLoadFragment : BaseFragment() {
    private var isLoadData: Boolean = false
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            tryLoadData()
        }
    }

    private fun tryLoadData() {
        if (!isParentHidden() && (!isLoadData || isDataEmpty())) {
            loadData()
            isLoadData = true
            dispatchChildLoadData()
        }
    }

    //防止嵌套的子Fragment，在首次能够拉到数据，因为首次需要判断父Fragment的可见性，该时候一起展示就会拉不到数据
    private fun dispatchChildLoadData() {
        val fragments = childFragmentManager.fragments
        for (item in fragments) {
            if (item is BaseLazyLoadFragment && item.isVisible) {
                item.tryLoadData()
            }
        }
    }

    private fun isParentHidden(): Boolean {
        val parentFragment = parentFragment
        if (parentFragment == null) {
            return false
        } else if (parentFragment is BaseLazyLoadFragment && !parentFragment.isHidden) {
            return false
        }
        return true
    }

    protected abstract fun loadData()
    protected abstract fun isDataEmpty(): Boolean
}