package com.chejdj.wanandroid_kotlin.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.home.ArticleListState
import com.chejdj.wanandroid_kotlin.ui.home.BannerState
import com.chejdj.wanandroid_kotlin.ui.home.HomeIntent
import com.chejdj.wanandroid_kotlin.ui.home.HomeState
import com.chejdj.wanandroid_kotlin.ui.home.model.HomeModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeState, HomeIntent>() {
    private val repo: HomeModel = HomeModel()
    private fun getBannerData() {
        viewModelScope.launch {
            val result = repo.getBannerData()
            executeResponse(
                result, {
                    result.data?.let {
                        sendUiState { copy(bannerState = BannerState.SUCCESS(it)) }
                    }
                }, {
                    Log.d(ERROR, "getBannerData fail")
                }
            )
        }
    }

    private fun getArticlesData(pageNum: Int) {
        viewModelScope.launch {
            val result = repo.getArticlesData(pageNum)
            executeResponse(result, {
                result.data?.let {
                    sendUiState { copy(articleListState = ArticleListState.SUCCESS(it)) }
                }
            }, { Log.d(ERROR, "getArticlesData fail") })
        }
    }

    override fun initState(): HomeState {
        return HomeState(BannerState.INIT, ArticleListState.INIT)
    }

    override fun handleIntent(uiIntent: HomeIntent) {
        when (uiIntent) {
            HomeIntent.GetAll -> {
                getBannerData()
                getArticlesData(0)
            }

            HomeIntent.GetBanner -> {
                getBannerData()
            }

            is HomeIntent.GetArticle -> {
                getArticlesData(uiIntent.pageNum)
            }
        }
    }
}