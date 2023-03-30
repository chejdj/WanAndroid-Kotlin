package com.chejdj.wanandroid_kotlin.ui.search.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.search.contract.SearchContract

class SearchModel : SearchContract.Model {
    override suspend fun getHotKeys(): BaseRes<List<HotKeyBean>?> {
        return DataManager.getHotKeys()
    }

    override suspend fun getSearchResults(key: String, pageNum: Int): BaseRes<ArticleData?> {
        return DataManager.getSearchResults(pageNum, key)
    }
}