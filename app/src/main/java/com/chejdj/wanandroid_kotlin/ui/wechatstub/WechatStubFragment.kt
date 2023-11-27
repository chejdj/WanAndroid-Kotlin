package com.chejdj.wanandroid_kotlin.ui.wechatstub

import androidx.lifecycle.ViewModelProvider
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.viewmodel.WechatViewModel

class WechatStubFragment : BaseTagFragment() {
    override fun loadData() {
        val viewModel = ViewModelProvider(this)[WechatViewModel::class.java]
        viewModel.getWechatChapters()
        viewModel.tags.observe(this) {
            showTags(it)
        }
    }

    override fun initView() {
        title.setText(R.string.menu_wechat_sub)
        type = 0
    }
}