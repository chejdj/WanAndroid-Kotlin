package com.chejdj.wanandroid_kotlin.ui.wechatstub.viewmodel

import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.wechatstub.WechatIntent
import com.chejdj.wanandroid_kotlin.ui.wechatstub.WechatState
import com.chejdj.wanandroid_kotlin.ui.wechatstub.model.WechatStubModel
import kotlinx.coroutines.launch

class WechatViewModel : BaseViewModel<WechatState, WechatIntent>() {
    private var model = WechatStubModel()
    private fun getWechatChapters() {
        viewModelScope.launch {
            val result = model.getWechatChapters()
            executeResponse(result, {
                result.data?.let {
                    sendUiState { copy(data = it) }
                }
            }, {})
        }
    }

    override fun initState(): WechatState {
        return WechatState(emptyList())
    }

    override fun handleIntent(uiIntent: WechatIntent) {
        when (uiIntent) {
            WechatIntent.GetChapters -> {
                getWechatChapters()
            }
        }
    }
}