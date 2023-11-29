package com.chejdj.wanandroid_kotlin.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : IUiState, UiIntent : IUiIntent> : ViewModel() {
    //数据流
    private val _uiStateFlow = MutableStateFlow(initState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    //事件流
    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    private val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()
    protected abstract fun initState(): UiState

    protected fun sendUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update {
            copy(_uiStateFlow.value)
        }
    }

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }

    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }

    protected abstract fun handleIntent(uiIntent: UiIntent)
}