package com.chejdj.wanandroid_kotlin.ui.architecture.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.architecture.ArchitectureIntent
import com.chejdj.wanandroid_kotlin.ui.architecture.ArchitectureState
import com.chejdj.wanandroid_kotlin.ui.architecture.model.ArchitectureModel
import kotlinx.coroutines.launch

class ArchitectureViewModel : BaseViewModel<ArchitectureState, ArchitectureIntent>() {
    private val model = ArchitectureModel()

    private fun getArchitectureData() {
        viewModelScope.launch {
            val result = model.getArchitectureData()
            executeResponse(result, {
                result.data?.let {
                    sendUiState { copy(data = it) }
                }
            }, {
                Log.d(ERROR, "getArchitectureData fail")
            })
        }
    }

    override fun initState(): ArchitectureState {
        return ArchitectureState(emptyList())
    }

    override fun handleIntent(uiIntent: ArchitectureIntent) {
        when (uiIntent) {
            ArchitectureIntent.GetArchitecture -> {
                getArchitectureData()
            }
        }
    }
}