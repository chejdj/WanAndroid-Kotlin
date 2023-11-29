package com.chejdj.wanandroid_kotlin.ui.search

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class SearchIntent : IUiIntent {
    object GetHotKeys : SearchIntent()

    data class GetResults(val key: String, val pageNum: Int) : SearchIntent()
}