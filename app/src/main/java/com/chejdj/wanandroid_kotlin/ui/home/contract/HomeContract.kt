package com.chejdj.wanandroid_kotlin.ui.home.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

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
        suspend fun getBannerData(): BaseRes<List<HomeBannerBean>>
        suspend fun getArticlesData(pageNum: Int): BaseRes<ArticleData>
    }
}