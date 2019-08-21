package com.chejdj.wanandroid_kotlin.ui.commons.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import io.reactivex.Observable

interface CommonArticleContract {
    interface View {
        fun showArticleDatas(data: List<Article>)
    }

    interface Model {
        fun getArticleData(cid: Int, pageCount: Int, type: Int): Observable<BaseRes<ArticleData>>
    }

    interface Presenter {
        fun getArticleData(cid: Int, pageCount: Int, type: Int)
    }
}