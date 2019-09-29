package com.chejdj.wanandroid_kotlin.ui.search.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.search.contract.SearchContract
import com.chejdj.wanandroid_kotlin.ui.search.model.SearchModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(private val view: SearchContract.View, private val scope: CoroutineScope) :
    SearchContract.Presenter, CoroutineScope by scope {
    private val model = SearchModel()
    override fun getHotKeys() {
        launch {
            val result = withContext(Dispatchers.IO) { model.getHotKeys() }
            executeResponse(result, {
                if (result.errorCode == 0) {
                    result.data?.let {
                        view.showHotKeys(it)
                    }
                }
            }, { Log.d(ERROR, "getHotKeys fail") })

        }
    }

    override fun getSearchResults(key: String, pageNum: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { model.getSearchResults(key, pageNum) }
            executeResponse(result, {
                if (result.errorCode == 0) {
                    result.data?.let {
                        view.showSearchResults(it)
                    }
                }
            }, {})
        }
    }
}