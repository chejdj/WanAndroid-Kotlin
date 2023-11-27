package com.chejdj.wanandroid_kotlin.ui.me.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.me.model.MeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeViewModel : ViewModel() {
    private val model = MeModel()
    val collectArticle: MutableLiveData<ArticleData> = MutableLiveData()
    fun getCollectArticles(pageNum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.getCollectArticles(pageNum)

            executeResponse(result, {
                if (result.data != null) {
                    collectArticle.postValue(result.data)
                }
            }, { Log.d(ERROR, "getCollectArticles fail") })
        }
    }
}