package com.chejdj.wanandroid_kotlin.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.ui.login.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val model = LoginModel()
    val loginResult: MutableLiveData<BaseRes<LoginBean?>> = MutableLiveData()

    fun login(accountName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = model.login(accountName, password)
            loginResult.postValue(result)
        }
    }
}