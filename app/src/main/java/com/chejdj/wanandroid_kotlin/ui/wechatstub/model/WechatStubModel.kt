package com.chejdj.wanandroid_kotlin.ui.wechatstub.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.wechatstub.contract.WechatStubContract
import kotlinx.coroutines.Deferred

class WechatStubModel : WechatStubContract.Model {
    override fun getWechatChapters(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return DataManager.getWechatArticleLists()
    }
}