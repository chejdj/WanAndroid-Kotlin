package com.chejdj.wanandroid_kotlin.ui.search.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import kotlinx.coroutines.Deferred

interface SearchContract {
    interface View {
        fun showHotKeys(hotkeys: List<HotKeyBean>)
        fun showSearchResults(articleData: ArticleData)
    }

    interface Presenter {
        fun getHotKeys()
        fun getSearchResults(key: String, pageNum: Int)
    }

    interface Model {
        fun getHotKeys(): Deferred<BaseRes<List<HotKeyBean>>>
        fun getSearchResults(key: String, pageNum: Int): Deferred<BaseRes<ArticleData>>
    }
}