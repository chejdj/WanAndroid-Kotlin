package com.chejdj.wanandroid_kotlin.ui.commons.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleIntent
import com.chejdj.wanandroid_kotlin.ui.commons.CommonArticleState
import com.chejdj.wanandroid_kotlin.ui.commons.model.CommonArticleModel
import kotlinx.coroutines.launch

class CommonViewModel : BaseViewModel<CommonArticleState, CommonArticleIntent>() {
    private val repo = CommonArticleModel()
    private fun getArticleData(cid: Int, pageCount: Int, type: Int) {
        viewModelScope.launch {
            val result = repo.getArticleData(cid, pageCount, type)
            executeResponse(result, {
                sendUiState { copy(data = result.data) }
            }, { Log.d(ERROR, "getArticleData fail") })
        }
    }

    override fun initState(): CommonArticleState {
        return CommonArticleState(null)
    }

    override fun handleIntent(uiIntent: CommonArticleIntent) {
        when (uiIntent) {
            is CommonArticleIntent.GetCommonArticle -> {
                getArticleData(uiIntent.cid, uiIntent.pageCount, uiIntent.type)
            }
        }
    }
}