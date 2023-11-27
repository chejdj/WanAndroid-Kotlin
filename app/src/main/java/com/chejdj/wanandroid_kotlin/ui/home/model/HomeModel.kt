package com.chejdj.wanandroid_kotlin.ui.home.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

class HomeModel {
     fun getBannerData(): BaseRes<List<HomeBannerBean>?> {
        return DataManager.getHomeBanner()
    }

     fun getArticlesData(pageNum: Int): BaseRes<ArticleData?> {
        return DataManager.getHomeArticles(pageNum)
    }
}