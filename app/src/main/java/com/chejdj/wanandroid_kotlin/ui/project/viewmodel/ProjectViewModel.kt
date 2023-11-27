package com.chejdj.wanandroid_kotlin.ui.project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.project.model.ProjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    private val model = ProjectModel()
    val tags: MutableLiveData<List<PrimaryArticleDirectoryBean>> = MutableLiveData()

    fun getProjectTags() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.getProjectTags()
            executeResponse(result, {
                result.data?.let {
                    tags.postValue(it)
                }
            }, {
                Log.d(ERROR, "getProjectTags fail")
            })
        }
    }
}