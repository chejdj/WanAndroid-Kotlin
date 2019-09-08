package com.chejdj.wanandroid_kotlin.ui.architecturedetail

import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import java.util.*

class ArchitectureDetailFragment : BaseTagFragment() {
    override fun loadData() {
        //nothing
    }

    @BindView(R.id.back)
    lateinit var backIv: ImageView

    override fun initView() {
        if (backIv.visibility == View.GONE) {
            backIv.visibility = View.VISIBLE
        }
        title.text = (activity as ArchitectureDetailActivity).getToolbarTitle()
        val data = (activity as ArchitectureDetailActivity).getSecondaryArticles()
        val tagList = ArrayList<String>()
        val cidNumbers = ArrayList<Int>()
        for (item in data) {
            tagList.add(item.name)
            cidNumbers.add(item.id)
        }
        showTags(tagList, cidNumbers)
    }

    @OnClick(R.id.back)
    fun finish() {
        activity?.finish()
    }
}