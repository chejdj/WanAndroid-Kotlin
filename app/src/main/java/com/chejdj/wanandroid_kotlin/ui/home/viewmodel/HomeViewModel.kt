package com.chejdj.wanandroid_kotlin.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.home.model.HomeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repo: HomeModel = HomeModel()
    val bannerLiveData: MutableLiveData<List<HomeBannerBean>?> = MutableLiveData()
    val articleData: MutableLiveData<ArticleData?> = MutableLiveData()

    fun start() {
        getBannerData()
        getArticlesData(0)
    }

    private fun getBannerData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getBannerData()
            executeResponse(
                result, {
                    bannerLiveData.postValue(result.data)
                }, {
                    Log.d(ERROR, "getBannerData fail")
                }
            )
        }
    }

    fun getArticlesData(pageNum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getArticlesData(pageNum)
            executeResponse(result, {
                articleData.postValue(result.data)
            }, { Log.d(ERROR, "getArticlesData fail") })
        }
    }
}