package com.chejdj.wanandroid_kotlin.ui.search.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

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
        suspend fun getHotKeys(): BaseRes<List<HotKeyBean>?>
        suspend fun getSearchResults(key: String, pageNum: Int): BaseRes<ArticleData?>
    }
}