package com.chejdj.wanandroid_kotlin.ui.wechatstub.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.wechatstub.contract.WechatStubContract
import io.reactivex.Observable

class WechatStubModel : WechatStubContract.Model {
    override fun getWechatChapters(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return DataManager.getWechatArticleLists()
    }
}