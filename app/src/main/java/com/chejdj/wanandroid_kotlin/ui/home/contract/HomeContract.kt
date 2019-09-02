package com.chejdj.wanandroid_kotlin.ui.home.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import io.reactivex.Observable
import kotlinx.coroutines.Deferred

interface HomeContract {
    interface View {
        fun showBannerData(data: List<HomeBannerBean>?)
        fun showArticlesData(data: ArticleData?)
    }

    interface Presenter {
        fun getBannerData()
        fun getArticlesData(pageNum: Int)
    }

    interface Model {
        suspend fun getBannerData(): Deferred<BaseRes<List<HomeBannerBean>>>
        fun getArticlesData(pageNum: Int): Observable<BaseRes<ArticleData>>
    }
}