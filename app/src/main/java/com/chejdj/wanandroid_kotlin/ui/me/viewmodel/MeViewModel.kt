package com.chejdj.wanandroid_kotlin.ui.me.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.me.MeIntent
import com.chejdj.wanandroid_kotlin.ui.me.MeUIState
import com.chejdj.wanandroid_kotlin.ui.me.model.MeModel
import kotlinx.coroutines.launch

class MeViewModel : BaseViewModel<MeUIState, MeIntent>() {
    private val model = MeModel()
    private fun getCollectArticles(pageNum: Int) {
        viewModelScope.launch {
            val result = model.getCollectArticles(pageNum)
            executeResponse(result, {
                if (result.data != null) {
                    sendUiState { copy(data = result.data!!) }
                }
            }, { Log.d(ERROR, "getCollectArticles fail") })
        }
    }

    override fun initState(): MeUIState {
        return MeUIState(data = ArticleData())
    }

    override fun handleIntent(uiIntent: MeIntent) {
        when (uiIntent) {
            is MeIntent.GetCollect -> {
                getCollectArticles(uiIntent.pageNum)
            }
        }
    }
}