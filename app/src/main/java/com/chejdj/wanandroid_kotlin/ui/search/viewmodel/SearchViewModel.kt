package com.chejdj.wanandroid_kotlin.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.search.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val model = SearchModel()

    val hotSearchWords: MutableLiveData<List<HotKeyBean>> = MutableLiveData()

    val searchResult: MutableLiveData<ArticleData> = MutableLiveData()

    fun getHotKeys() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.getHotKeys()
            executeResponse(result, {
                if (result.data != null) {
                    hotSearchWords.postValue(result.data)
                }
            }, {
                Log.d(ERROR, "getHotKeys fail")
            })
        }
    }

    fun getSearchResults(key: String, pageNum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.getSearchResults(key, pageNum)
            executeResponse(result, {
                result.data?.let {
                    searchResult.postValue(it)
                }
            }, {})
        }
    }
}