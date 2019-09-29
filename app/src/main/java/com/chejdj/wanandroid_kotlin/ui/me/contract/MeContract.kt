package com.chejdj.wanandroid_kotlin.ui.me.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import kotlinx.coroutines.Deferred

interface MeContract {
    interface View {
        fun showCollectArticles(data: ArticleData)
    }

    interface Presenter {
        fun getCollectArticles(pageNum: Int)
    }

    interface Model {
        suspend fun getCollectArticles(pageNum: Int): BaseRes<ArticleData>
    }
}