package com.chejdj.wanandroid_kotlin.ui.home

import com.chejdj.wanandroid_kotlin.base.IUiState
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

data class HomeState(val bannerState: BannerState, val articleListState: ArticleListState) :
    IUiState

sealed class BannerState {
    object INIT : BannerState()
    data class SUCCESS(val model: List<HomeBannerBean>) : BannerState()
}

sealed class ArticleListState {
    object INIT : ArticleListState()

    data class SUCCESS(val articles: ArticleData) : ArticleListState()
}