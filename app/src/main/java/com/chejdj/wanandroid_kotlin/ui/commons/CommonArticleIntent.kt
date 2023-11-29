package com.chejdj.wanandroid_kotlin.ui.commons

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class CommonArticleIntent : IUiIntent {
    data class GetCommonArticle(val cid: Int, val pageCount: Int, val type: Int) :
        CommonArticleIntent()
}