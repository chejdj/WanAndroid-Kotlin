package com.chejdj.wanandroid_kotlin.ui.login

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class LoginIntent : IUiIntent {
    data class Login(val userName: String, val password: String) : LoginIntent()

}