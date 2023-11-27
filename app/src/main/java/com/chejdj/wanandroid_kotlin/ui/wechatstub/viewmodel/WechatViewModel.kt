package com.chejdj.wanandroid_kotlin.ui.wechatstub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.wechatstub.model.WechatStubModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WechatViewModel : ViewModel() {
    private var model = WechatStubModel()
    val tags: MutableLiveData<List<PrimaryArticleDirectoryBean>> = MutableLiveData()
    fun getWechatChapters() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.IO) { model.getWechatChapters() }
            executeResponse(result, {
                result.data?.let {
                    tags.postValue(it)
                }
            }, {})
        }
    }
}