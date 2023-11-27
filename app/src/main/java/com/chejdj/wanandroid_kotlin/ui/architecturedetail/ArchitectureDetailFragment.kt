package com.chejdj.wanandroid_kotlin.ui.architecturedetail

import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
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
        val dataList = ArrayList<PrimaryArticleDirectoryBean>()
        for (item in data) {
            val data = PrimaryArticleDirectoryBean()
            data.id = item.id
            data.name = item.name
            dataList.add(data)
        }
        showTags(dataList)
    }

    @OnClick(R.id.back)
    fun finish() {
        activity?.finish()
    }
}