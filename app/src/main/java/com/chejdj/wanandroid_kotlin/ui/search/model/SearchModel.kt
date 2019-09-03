package com.chejdj.wanandroid_kotlin.ui.search.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.search.contract.SearchContract
import kotlinx.coroutines.Deferred

class SearchModel : SearchContract.Model {
    override fun getHotKeys(): Deferred<BaseRes<List<HotKeyBean>>> {
        return DataManager.getHotKeys()
    }

    override fun getSearchResults(key: String, pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return DataManager.getSearchResults(pageNum, key)
    }
}