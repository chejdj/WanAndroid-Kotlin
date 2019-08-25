package com.chejdj.wanandroid_kotlin.ui.wechatstub

import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseFragment
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.presenter.WechatStubPresenter

class WechatStubFragment : BaseTagFragment() {
    override fun initView() {
        title.setText(R.string.menu_wechat_sub)
        type = 0
        val presenter = WechatStubPresenter(this)
        presenter.getWechatChapters()
    }
}