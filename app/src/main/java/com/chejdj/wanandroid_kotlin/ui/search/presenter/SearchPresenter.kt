package com.chejdj.wanandroid_kotlin.ui.search.presenter

import com.chejdj.wanandroid_kotlin.ui.search.contract.SearchContract
import com.chejdj.wanandroid_kotlin.ui.search.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(private val view: SearchContract.View) : SearchContract.Presenter {
    private val model = SearchModel()
    override fun getHotKeys() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getHotKeys().await() }
            if (result.errorCode == 0) {
                result.data?.let {
                    view.showHotKeys(it)
                }
            }
        }
    }

    override fun getSearchResults(key: String, pageNum: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getSearchResults(key, pageNum).await() }
            if (result.errorCode == 0) {
                result.data?.let {
                    view.showSearchResults(it)
                }
            }
        }
    }
}