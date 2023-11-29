package com.chejdj.wanandroid_kotlin.ui.login

import com.chejdj.wanandroid_kotlin.base.IUiState
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean

data class LoginState(val data: BaseRes<LoginBean?>) : IUiState