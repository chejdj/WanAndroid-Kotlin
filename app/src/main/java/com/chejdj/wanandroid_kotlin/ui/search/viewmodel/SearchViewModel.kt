package com.chejdj.wanandroid_kotlin.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.search.SearchIntent
import com.chejdj.wanandroid_kotlin.ui.search.SearchState
import com.chejdj.wanandroid_kotlin.ui.search.model.SearchModel
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel<SearchState, SearchIntent>() {
    private val model = SearchModel()

    private fun getHotKeys() {
        viewModelScope.launch {
            val result = model.getHotKeys()
            executeResponse(result, {
                result.data?.let {
                    sendUiState {
                        copy(hotKey = it)
                    }
                }
            }, {
                Log.d(ERROR, "getHotKeys fail")
            })
        }
    }

    private fun getSearchResults(key: String, pageNum: Int) {
        viewModelScope.launch {
            val result = model.getSearchResults(key, pageNum)
            executeResponse(result, {
                result.data?.let {
                    sendUiState { copy(result = it) }
                }
            }, {})
        }
    }

    override fun initState(): SearchState {
        return SearchState(emptyList(), ArticleData())
    }

    override fun handleIntent(uiIntent: SearchIntent) {
        when (uiIntent) {
            SearchIntent.GetHotKeys -> {
                getHotKeys()
            }

            is SearchIntent.GetResults -> {
                getSearchResults(uiIntent.key, uiIntent.pageNum)
            }
        }
    }
}