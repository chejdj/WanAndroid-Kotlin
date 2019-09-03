package com.chejdj.wanandroid_kotlin.ui.wechatstub.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import kotlinx.coroutines.Deferred

interface WechatStubContract {
    interface Presenter {
        fun getWechatChapters()
    }

    interface Model {
        fun getWechatChapters(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>>
    }
}