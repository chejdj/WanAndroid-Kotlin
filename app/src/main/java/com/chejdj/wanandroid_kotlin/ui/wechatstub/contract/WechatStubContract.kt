package com.chejdj.wanandroid_kotlin.ui.wechatstub.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import io.reactivex.Observable

interface WechatStubContract {
    interface Presenter {
        fun getWechatChapters()
    }

    interface Model {
        fun getWechatChapters(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>>
    }
}