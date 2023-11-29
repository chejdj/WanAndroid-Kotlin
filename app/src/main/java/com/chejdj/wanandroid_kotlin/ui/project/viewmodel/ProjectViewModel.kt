package com.chejdj.wanandroid_kotlin.ui.project.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.project.ProjectIntent
import com.chejdj.wanandroid_kotlin.ui.project.ProjectState
import com.chejdj.wanandroid_kotlin.ui.project.model.ProjectModel
import kotlinx.coroutines.launch

class ProjectViewModel : BaseViewModel<ProjectState, ProjectIntent>() {
    private val model = ProjectModel()
    private fun getProjectTags() {
        viewModelScope.launch {
            val result = model.getProjectTags()
            executeResponse(result, {
                result.data?.let {
                    sendUiState { copy(data = it) }
                }
            }, {
                Log.d(ERROR, "getProjectTags fail")
            })
        }
    }

    override fun initState(): ProjectState {
        return ProjectState(emptyList())
    }

    override fun handleIntent(uiIntent: ProjectIntent) {
        when (uiIntent) {
            ProjectIntent.GetTags -> {
                getProjectTags()
            }
        }
    }
}