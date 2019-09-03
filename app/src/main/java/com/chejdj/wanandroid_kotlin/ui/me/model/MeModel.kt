package com.chejdj.wanandroid_kotlin.ui.me.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import kotlinx.coroutines.Deferred

class MeModel : MeContract.Model {
    override fun getCollectArticles(pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return DataManager.getCollectedArticle(pageNum)
    }
}