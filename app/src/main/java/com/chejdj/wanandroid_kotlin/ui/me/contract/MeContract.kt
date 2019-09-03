package com.chejdj.wanandroid_kotlin.ui.me.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import kotlinx.coroutines.Deferred

interface MeContract {
    interface View {
        fun showCollectArticles(data: ArticleData)
    }

    interface Presenter {
        fun getCollectArtilces(pageNum: Int)
    }

    interface Model {
        fun getCollectArticles(pageNum: Int): Deferred<BaseRes<ArticleData>>
    }
}