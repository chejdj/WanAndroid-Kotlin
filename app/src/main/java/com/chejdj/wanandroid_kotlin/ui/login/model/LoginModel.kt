package com.chejdj.wanandroid_kotlin.ui.login.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.ui.login.contract.LoginContract
import kotlinx.coroutines.Deferred

class LoginModel : LoginContract.Model {
    override fun login(accountName: String, password: String): Deferred<BaseRes<LoginBean>> {
        return DataManager.login(accountName, password)
    }
}