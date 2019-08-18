package com.chejdj.wanandroid_kotlin.ui.home.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import io.reactivex.Observable

interface HomeContract {
    interface View {
        fun shwoBannerData(data: List<HomeBannerBean>?)
        fun showArticlesData(data: ArticleData?)
    }

    interface Presenter {
        fun getBannerData()
        fun getArticlesData(pageNum: Int)
    }

    interface Model {
        fun getBannerData(): Observable<BaseRes<List<HomeBannerBean>>>
        fun getArticlesData(pageNum: Int): Observable<BaseRes<ArticleData>>
    }
}