package com.chejdj.wanandroid_kotlin.ui.home.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.home.contract.HomeContract
import io.reactivex.Observable
import kotlinx.coroutines.Deferred

class HomeModel : HomeContract.Model {
    override suspend fun getBannerData(): Deferred<BaseRes<List<HomeBannerBean>>> {
        return DataManager.getHomeBanner()
    }

    override fun getArticlesData(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return DataManager.getHomeArticles(pageNum)
    }
}