package com.chejdj.wanandroid_kotlin.ui.architecture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.architecture.model.ArchitectureModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArchitectureViewModel : ViewModel() {
    private val model = ArchitectureModel()

    val architectureList: MutableLiveData<List<PrimaryArticleDirectoryBean>?> =
        MutableLiveData()


    fun getArchitectureData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.getArchitectureData()
            executeResponse(result, {
                architectureList.postValue(result.data)
            }, {
                Log.d(ERROR, "getArchitectureData fail")
            })
        }
    }
}