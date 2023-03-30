package com.chejdj.wanandroid_kotlin.ui.commons.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import kotlinx.coroutines.Deferred

interface CommonArticleContract {
    interface View {
        fun showArticleDatas(data: ArticleData)
    }

    interface Model {
        suspend fun getArticleData(cid: Int, pageCount: Int, type: Int): BaseRes<ArticleData?>
    }

    interface Presenter {
        fun getArticleData(cid: Int, pageCount: Int, type: Int)
        fun releaseResource()
    }
}