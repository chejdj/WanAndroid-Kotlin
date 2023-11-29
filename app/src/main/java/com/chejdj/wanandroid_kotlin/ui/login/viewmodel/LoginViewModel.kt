package com.chejdj.wanandroid_kotlin.ui.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.base.BaseViewModel
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.ui.login.LoginIntent
import com.chejdj.wanandroid_kotlin.ui.login.LoginState
import com.chejdj.wanandroid_kotlin.ui.login.model.LoginModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel<LoginState, LoginIntent>() {
    private val model = LoginModel()
    private fun login(accountName: String, password: String) {
        viewModelScope.launch {
            val result = model.login(accountName, password)
            sendUiState { copy(data = result) }
        }
    }

    override fun initState(): LoginState {
        return LoginState(BaseRes())
    }

    override fun handleIntent(uiIntent: LoginIntent) {
        when (uiIntent) {
            is LoginIntent.Login -> {
                login(uiIntent.userName, uiIntent.password)
            }
        }
    }
}