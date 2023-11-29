package com.chejdj.wanandroid_kotlin.ui.home

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class HomeIntent : IUiIntent {

    object GetAll : HomeIntent()
    object GetBanner : HomeIntent()
    data class GetArticle(val pageNum: Int) : HomeIntent()
}