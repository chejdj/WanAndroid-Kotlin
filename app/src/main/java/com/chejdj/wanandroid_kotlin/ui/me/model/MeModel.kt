package com.chejdj.wanandroid_kotlin.ui.me.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

class MeModel {
    suspend fun getCollectArticles(pageNum: Int): BaseRes<ArticleData?> {
        return DataManager.getCollectedArticle(pageNum)
    }
}