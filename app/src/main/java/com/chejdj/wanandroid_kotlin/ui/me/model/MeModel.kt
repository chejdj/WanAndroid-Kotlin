package com.chejdj.wanandroid_kotlin.ui.me.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract

class MeModel : MeContract.Model {
    override suspend fun getCollectArticles(pageNum: Int): BaseRes<ArticleData?> {
        return DataManager.getCollectedArticle(pageNum)
    }
}