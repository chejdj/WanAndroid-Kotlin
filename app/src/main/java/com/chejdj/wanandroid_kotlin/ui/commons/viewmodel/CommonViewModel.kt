package com.chejdj.wanandroid_kotlin.ui.commons.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.commons.model.CommonArticleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommonViewModel : ViewModel() {
    private val repo = CommonArticleModel()
    val commonArticleData: MutableLiveData<ArticleData?> = MutableLiveData()

    fun getArticleData(cid: Int, pageCount: Int, type: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getArticleData(cid, pageCount, type)
            executeResponse(result, {
                commonArticleData.postValue(result.data)
            }, { Log.d(ERROR, "getArticleData fail") })
        }
    }
}