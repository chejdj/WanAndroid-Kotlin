package com.chejdj.wanandroid_kotlin.ui.search

import com.chejdj.wanandroid_kotlin.base.IUiState
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

data class SearchState(val hotKey: List<HotKeyBean>, val result: ArticleData) : IUiState